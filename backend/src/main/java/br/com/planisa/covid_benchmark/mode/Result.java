package br.com.planisa.covid_benchmark.mode;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cases_country_1")
    private Integer casesCountry1;

    @Column(name = "cases_country_2")
    private Integer casesCountry2;

    @Column(name = "deaths_country_1")
    private Integer deathsCountry1;

    @Column(name = "deaths_country_2")
    private Integer deathsCountry2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "benchmark_id", referencedColumnName = "id")
    private Benchmark benchmark;

    @CreatedDate
    private LocalDate createtAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    public Result() {
    }

    public Result(Integer id, Integer casesCountry1, Integer casesCountry2, Integer deathsCountry1, Integer deathsCountry2, Benchmark benchmark, LocalDate createtAt, LocalDate updatedAt) {
        this.id = id;
        this.casesCountry1 = casesCountry1;
        this.casesCountry2 = casesCountry2;
        this.deathsCountry1 = deathsCountry1;
        this.deathsCountry2 = deathsCountry2;
        this.benchmark = benchmark;
        this.createtAt = createtAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCasesCountry1() {
        return casesCountry1;
    }

    public void setCasesCountry1(Integer casesCountry1) {
        this.casesCountry1 = casesCountry1;
    }

    public Integer getCasesCountry2() {
        return casesCountry2;
    }

    public void setCasesCountry2(Integer casesCountry2) {
        this.casesCountry2 = casesCountry2;
    }

    public Integer getDeathsCountry1() {
        return deathsCountry1;
    }

    public void setDeathsCountry1(Integer deathsCountry1) {
        this.deathsCountry1 = deathsCountry1;
    }

    public Integer getDeathsCountry2() {
        return deathsCountry2;
    }

    public void setDeathsCountry2(Integer deathsCountry2) {
        this.deathsCountry2 = deathsCountry2;
    }

    public Benchmark getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public LocalDate getCreatetAt() {
        return createtAt;
    }

    public void setCreatetAt(LocalDate createtAt) {
        this.createtAt = createtAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", casesCountry1=" + casesCountry1 +
                ", casesCountry2=" + casesCountry2 +
                ", deathsCountry1=" + deathsCountry1 +
                ", deathsCountry2=" + deathsCountry2 +
                ", benchmark=" + benchmark +
                ", createtAt=" + createtAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
