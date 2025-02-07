package br.com.planisa.covid_benchmark;

import br.com.planisa.covid_benchmark.dto.BenchmarkDTO;
import br.com.planisa.covid_benchmark.model.Benchmark;
import br.com.planisa.covid_benchmark.model.Result;
import br.com.planisa.covid_benchmark.repository.BenchmarkRepository;
import br.com.planisa.covid_benchmark.service.BenchmarkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BenchmarkServiceTest {

    @Mock
    private BenchmarkRepository benchmarkRepository;

    @InjectMocks
    private BenchmarkService benchmarkService;

    @Test
    void shouldListAllBenchmarks() {
        List<Benchmark> benchmarks = List.of(
                createBenchmark(1L, "Test 1"),
                createBenchmark(2L, "Test 2")
        );
        when(benchmarkRepository.findAll()).thenReturn(benchmarks);

        List<BenchmarkDTO> result = benchmarkService.getBenchmarks();

        assertEquals(2, result.size());
        assertEquals("Test 1", result.get(0).getName());
        assertEquals("Test 2", result.get(1).getName());
        verify(benchmarkRepository).findAll();
    }

    @Test
    void shouldGetBenchmarkById() {
        Long id = 1L;
        Benchmark benchmark = createBenchmark(id, "Test");
        when(benchmarkRepository.findById(id)).thenReturn(Optional.of(benchmark));

        BenchmarkDTO result = benchmarkService.getBenchmarkById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Test", result.getName());
        verify(benchmarkRepository).findById(id);
    }

    private Benchmark createBenchmark(Long id, String name) {
        Benchmark benchmark = new Benchmark();
        benchmark.setId(id);
        benchmark.setName(name);
        benchmark.setCountry1("Country1");
        benchmark.setCountry2("Country2");
        benchmark.setStartDate(LocalDate.now());
        benchmark.setEndDate(LocalDate.now().plusDays(30));
        benchmark.setCreatetAt(LocalDateTime.now());
        benchmark.setUpdatedAt(LocalDateTime.now());

        Result result = new Result();
        result.setId(1);
        result.setCasesCountry1(100);
        result.setCasesCountry2(200);
        result.setDeathsCountry1(10);
        result.setDeathsCountry2(20);
        result.setBenchmark(benchmark);
        result.setCreatetAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());

        benchmark.setResult(result);
        return benchmark;
    }
}