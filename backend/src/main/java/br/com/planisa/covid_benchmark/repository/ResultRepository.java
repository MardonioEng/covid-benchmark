package br.com.planisa.covid_benchmark.repository;

import br.com.planisa.covid_benchmark.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
