package br.com.planisa.covid_benchmark.controller;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.dto.BenchmarkSaveDTO;
import br.com.planisa.covid_benchmark.dto.BenchmarkUpdateDTO;
import br.com.planisa.covid_benchmark.service.BenchmarkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/benchmarks")
public class BenchmarkController implements GenericController{

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

    @PostMapping
    public ResponseEntity<BenchmarkDTO> saveBenchmark(@RequestBody @Valid BenchmarkSaveDTO benchmarkSaveDTO) {
        BenchmarkDTO benchmarkDTO = benchmarkService.saveBenchmark(benchmarkSaveDTO);
        URI location = this.generateHeaderLocation(benchmarkDTO.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BenchmarkDTO> updateBenchmark(@PathVariable Long id, @RequestBody @Valid BenchmarkUpdateDTO benchmarkUpdateDTO) {
        try {
            BenchmarkDTO updated = benchmarkService.updateBenchmark(id, benchmarkUpdateDTO.getName());
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenchmark(@PathVariable Long id) {
        try {
            benchmarkService.deleteBenchmark(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
