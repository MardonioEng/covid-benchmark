package br.com.planisa.covid_benchmark.service;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.model.Benchmark;
import br.com.planisa.covid_benchmark.repository.BenchmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenchmarkService {

    private final BenchmarkRepository benchmarkRepository;

    public BenchmarkService(BenchmarkRepository benchmarkRepository) {
        this.benchmarkRepository = benchmarkRepository;
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

    public BenchmarkDTO updateBenchmark(Long id, String name) throws Exception {
        Benchmark benchmark = benchmarkRepository.findById(id)
                .orElseThrow(() -> new Exception("Benchmark não encontrado"));

        benchmark.setName(name);
        benchmark = benchmarkRepository.save(benchmark);
        return new BenchmarkDTO(benchmark);
    }

    public void deleteBenchmark(Long id) throws Exception {
        Benchmark benchmark = benchmarkRepository.findById(id)
                .orElseThrow(() -> new Exception("Benchmark não encontrado"));

        benchmarkRepository.delete(benchmark);
    }

}
