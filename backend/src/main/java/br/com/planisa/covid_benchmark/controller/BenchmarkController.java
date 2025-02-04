package br.com.planisa.covid_benchmark.controller;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.service.BenchmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benchmarks")
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    public BenchmarkController(BenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @GetMapping
    public ResponseEntity<List<BenchmarkDTO>> getBenchmarks() {
        List<BenchmarkDTO> benchmarkDTOS = benchmarkService.getBenchmarks();
        return ResponseEntity.ok(benchmarkDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenchmarkDTO> getBenchmarkById(@PathVariable Long id) {
        BenchmarkDTO benchmarkDTO = benchmarkService.getBenchmarkById(id);
        if (benchmarkDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(benchmarkDTO);
    }
}
