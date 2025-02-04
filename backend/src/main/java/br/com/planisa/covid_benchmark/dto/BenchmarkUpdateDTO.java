package br.com.planisa.covid_benchmark.dto;

public class BenchmarkUpdateDTO {

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
