package br.com.planisa.covid_benchmark.repository;

import br.com.planisa.covid_benchmark.model.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {

    Benchmark findByName(String name);

}
