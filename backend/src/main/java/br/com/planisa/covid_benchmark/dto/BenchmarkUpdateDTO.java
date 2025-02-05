package br.com.planisa.covid_benchmark.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BenchmarkUpdateDTO {

    @NotBlank(message = "Field required")
    @Size(min = 2, max = 150, message = "field outside the standard size")
    private String name;

    public BenchmarkUpdateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BenchmarkUpdateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
