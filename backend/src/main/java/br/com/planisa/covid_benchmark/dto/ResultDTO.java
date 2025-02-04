package br.com.planisa.covid_benchmark.dto;

import br.com.planisa.covid_benchmark.model.Result;

import java.time.LocalDateTime;

public class ResultDTO {

    private Integer id;
    private Integer casesCountry1;
    private Integer casesCountry2;
    private Integer deathsCountry1;
    private Integer deathsCountry2;
    private LocalDateTime createtAt;
    private LocalDateTime updatedAt;

    public ResultDTO(Result result) {
        this.id = result.getId();
        this.casesCountry1 = result.getCasesCountry1();
        this.casesCountry2 = result.getCasesCountry2();
        this.deathsCountry1 = result.getDeathsCountry1();
        this.deathsCountry2 = result.getDeathsCountry2();
        this.createtAt = result.getCreatetAt();
        this.updatedAt = result.getUpdatedAt();
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

    @Override
    public String toString() {
        return "ResultDTO{" +
                "id=" + id +
                ", casesCountry1=" + casesCountry1 +
                ", casesCountry2=" + casesCountry2 +
                ", deathsCountry1=" + deathsCountry1 +
                ", deathsCountry2=" + deathsCountry2 +
                ", createtAt=" + createtAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
