package br.com.planisa.covid_benchmark.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class BenchmarkSaveDTO {

    @NotBlank(message = "Field required")
    @Size(min = 2, max = 150, message = "field outside the standard size")
    private String name;

    @NotBlank(message = "Field required")
    @Size(min = 2, max = 100, message = "field outside the standard size")
    private String country1;

    @NotBlank(message = "Field required")
    @Size(min = 2, max = 100, message = "field outside the standard size")
    private String country2;

    @NotNull(message = "Field required")
    @Past()
    private LocalDate startDate;

    @NotNull(message = "Field required")
    private LocalDate endDate;

    public BenchmarkSaveDTO() {
    }

    public BenchmarkSaveDTO(String name, String country1, String country2, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.country1 = country1;
        this.country2 = country2;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public @NotBlank(message = "Field required") @Size(min = 2, max = 150, message = "field outside the standard size") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Field required") @Size(min = 2, max = 150, message = "field outside the standard size") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Field required") @Size(min = 2, max = 100, message = "field outside the standard size") String getCountry1() {
        return country1;
    }

    public void setCountry1(@NotBlank(message = "Field required") @Size(min = 2, max = 100, message = "field outside the standard size") String country1) {
        this.country1 = country1;
    }

    public @NotBlank(message = "Field required") @Size(min = 2, max = 100, message = "field outside the standard size") String getCountry2() {
        return country2;
    }

    public void setCountry2(@NotBlank(message = "Field required") @Size(min = 2, max = 100, message = "field outside the standard size") String country2) {
        this.country2 = country2;
    }

    public @NotNull(message = "Field required") @Past() LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Field required") @Past() LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = "Field required") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "Field required") LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BenchmarkSaveDTO{" +
                "name='" + name + '\'' +
                ", country1='" + country1 + '\'' +
                ", country2='" + country2 + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
