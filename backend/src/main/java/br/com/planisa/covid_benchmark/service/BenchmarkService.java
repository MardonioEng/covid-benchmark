package br.com.planisa.covid_benchmark.service;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.model.Benchmark;
import br.com.planisa.covid_benchmark.repository.BenchmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenchmarkService {

    private final BenchmarkRepository BenchmarkRepository;

    public BenchmarkService(BenchmarkRepository benchmarkRepository) {
        BenchmarkRepository = benchmarkRepository;
    }

    public List<BenchmarkDTO> getBenchmarks() {
        List<Benchmark> benchmarkList = BenchmarkRepository.findAll();
        return benchmarkList
                .stream()
                .map(BenchmarkDTO::new)
                .toList();
    }

    public BenchmarkDTO getBenchmarkById(Long id) {
        Optional<Benchmark> benchmark = BenchmarkRepository.findById(id);
        return benchmark.map(BenchmarkDTO::new).orElse(null);
    }

}
