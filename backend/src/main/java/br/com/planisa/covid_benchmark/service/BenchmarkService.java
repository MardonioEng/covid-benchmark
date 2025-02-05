package br.com.planisa.covid_benchmark.service;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.dto.BenchmarkSaveDTO;
import br.com.planisa.covid_benchmark.exceptions.ResourceAlreadyExistsException;
import br.com.planisa.covid_benchmark.model.Benchmark;
import br.com.planisa.covid_benchmark.model.Result;
import br.com.planisa.covid_benchmark.model.external.ApiClientData;
import br.com.planisa.covid_benchmark.model.external.DailyData;
import br.com.planisa.covid_benchmark.repository.BenchmarkRepository;
import br.com.planisa.covid_benchmark.repository.ResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BenchmarkService {

    private final BenchmarkRepository benchmarkRepository;
    private final ApiClientService apiClientService;
    private final ResultRepository resultRepository;

    public BenchmarkService(BenchmarkRepository benchmarkRepository, ApiClientService apiClientService, ResultRepository resultRepository) {
        this.benchmarkRepository = benchmarkRepository;
        this.apiClientService = apiClientService;
        this.resultRepository = resultRepository;
    }

    public List<BenchmarkDTO> getBenchmarks() {
        List<Benchmark> benchmarkList = benchmarkRepository.findAll();
        return benchmarkList
                .stream()
                .map(BenchmarkDTO::new)
                .toList();
    }

    public BenchmarkDTO getBenchmarkById(Long id) {
        Optional<Benchmark> benchmark = benchmarkRepository.findById(id);
        return benchmark.map(BenchmarkDTO::new).orElse(null);
    }

    @Transactional
    public BenchmarkDTO saveBenchmark(BenchmarkSaveDTO benchmarkSaveDTO) {

        Benchmark savedBenchmark = benchmarkRepository.findByName(benchmarkSaveDTO.getName());
        if (savedBenchmark != null) {
            throw new ResourceAlreadyExistsException("Benchmark with name " + benchmarkSaveDTO.getName() + " already exists");
        }

        ApiClientData[] responseDataCountry1 = apiClientService.getCovidData(benchmarkSaveDTO.getCountry1());
        ApiClientData[] responseDataCountry2 = apiClientService.getCovidData(benchmarkSaveDTO.getCountry2());

        List<ApiClientData> responseFilteredDataCountry1 = filterByDateRange(List.of(responseDataCountry1), benchmarkSaveDTO.getStartDate(), benchmarkSaveDTO.getEndDate());
        List<ApiClientData> responseFilteredDataCountry2 = filterByDateRange(List.of(responseDataCountry2), benchmarkSaveDTO.getStartDate(), benchmarkSaveDTO.getEndDate());

        Map<String, Integer> totalsCountry1 = this.calculateTotals(responseFilteredDataCountry1);
        Map<String, Integer> totalsCountry2 = this.calculateTotals(responseFilteredDataCountry2);

        Benchmark benchmark = new Benchmark();
        benchmark.setName(benchmarkSaveDTO.getName());
        benchmark.setCountry1(benchmarkSaveDTO.getCountry1());
        benchmark.setCountry2(benchmarkSaveDTO.getCountry2());
        benchmark.setStartDate(benchmarkSaveDTO.getStartDate());
        benchmark.setEndDate(benchmarkSaveDTO.getEndDate());
        benchmark.setCreatetAt(LocalDateTime.now());
        benchmark.setUpdatedAt(LocalDateTime.now());


        Benchmark benchmarkEntity = benchmarkRepository.save(benchmark);

        Result result = new Result();
        result.setBenchmark(benchmarkEntity);
        result.setCasesCountry1(totalsCountry1.get("cases"));
        result.setCasesCountry2(totalsCountry2.get("cases"));
        result.setDeathsCountry1(totalsCountry1.get("deaths"));
        result.setDeathsCountry2(totalsCountry2.get("deaths"));
        result.setCreatetAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());

        Result resultEntity = resultRepository.save(result);

        benchmarkEntity.setResult(resultEntity);

        return new BenchmarkDTO(benchmarkEntity);
    }

    public BenchmarkDTO updateBenchmark(Long id, String name) throws Exception {
        Benchmark benchmark = benchmarkRepository.findById(id)
                .orElseThrow(() -> new Exception("Benchmark não encontrado"));

        benchmark.setName(name);
        benchmark.setUpdatedAt(LocalDateTime.now());
        benchmark = benchmarkRepository.save(benchmark);
        return new BenchmarkDTO(benchmark);
    }

    public void deleteBenchmark(Long id) throws Exception {
        Benchmark benchmark = benchmarkRepository.findById(id)
                .orElseThrow(() -> new Exception("Benchmark não encontrado"));

        benchmarkRepository.delete(benchmark);
    }

    private List<ApiClientData> filterByDateRange(List<ApiClientData> data, LocalDate startDate, LocalDate endDate) {
        return data.stream()
                .map(clientData -> {
                    ApiClientData filteredData = new ApiClientData();
                    filteredData.setCountry(clientData.getCountry());
                    filteredData.setRegion(clientData.getRegion());

                    Map<String, DailyData> filteredCases = filterDailyDataByDateRange(
                            clientData.getCases(),
                            startDate,
                            endDate
                    );
                    filteredData.setCases(filteredCases);

                    Map<String, DailyData> filteredDeaths = filterDailyDataByDateRange(
                            clientData.getDeaths(),
                            startDate,
                            endDate
                    );
                    filteredData.setDeaths(filteredDeaths);

                    return filteredData;
                })
                .collect(Collectors.toList());
    }

    private Map<String, DailyData> filterDailyDataByDateRange(
            Map<String, DailyData> dailyData,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (dailyData == null) {
            return new HashMap<>();
        }

        return dailyData.entrySet().stream()
                .filter(entry -> {
                    LocalDate date = LocalDate.parse(entry.getKey());
                    return !date.isBefore(startDate) && !date.isAfter(endDate);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private Map<String, Integer> calculateTotals(List<ApiClientData> dataList) {
        Map<String, Integer> totals = new HashMap<>();
        int totalNewCases = 0;
        int totalNewDeaths = 0;

        for (ApiClientData data : dataList) {
            if (data.getCases() != null) {
                totalNewCases += data.getCases().values().stream()
                        .filter(Objects::nonNull)
                        .mapToInt(dailyData -> dailyData.getNewCount() != null ? dailyData.getNewCount().intValue() : 0)
                        .sum();
            }

            if (data.getDeaths() != null) {
                totalNewDeaths += data.getDeaths().values().stream()
                        .filter(Objects::nonNull)
                        .mapToInt(dailyData -> dailyData.getNewCount() != null ? dailyData.getNewCount().intValue() : 0)
                        .sum();
            }
        }

        totals.put("cases", totalNewCases);
        totals.put("deaths", totalNewDeaths);

        return totals;
    }
}
