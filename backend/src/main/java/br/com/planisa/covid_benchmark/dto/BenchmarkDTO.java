package br.com.planisa.covid_benchmark.dto;

import br.com.planisa.covid_benchmark.model.Benchmark;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BenchmarkDTO {

    private Long id;
    private String name;
    private String country1;
    private String country2;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createtAt;
    private LocalDateTime updatedAt;
    private ResultDTO result;

    public BenchmarkDTO(Benchmark benchmark) {
        this.id = benchmark.getId();
        this.name = benchmark.getName();
        this.country1 = benchmark.getCountry1();
        this.country2 = benchmark.getCountry2();
        this.startDate = benchmark.getStartDate();
        this.endDate = benchmark.getEndDate();
        this.createtAt = benchmark.getCreatetAt();
        this.updatedAt = benchmark.getUpdatedAt();
        this.result = new ResultDTO(benchmark.getResult());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatetAt() {
        return createtAt;
    }

    public void setCreatetAt(LocalDateTime createtAt) {
        this.createtAt = createtAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BenchmarkDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country1='" + country1 + '\'' +
                ", country2='" + country2 + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createtAt=" + createtAt +
                ", updatedAt=" + updatedAt +
                ", resultDTO=" + result +
                '}';
    }
}
