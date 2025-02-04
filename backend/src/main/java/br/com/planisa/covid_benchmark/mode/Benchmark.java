package br.com.planisa.covid_benchmark.mode;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "benchmark")
public class Benchmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "country_1", length = 100, nullable = false)
    private String country1;

    @Column(name = "country_2", length = 100, nullable = false)
    private String country2;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createtAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Benchmark() {
    }

    public Benchmark(Integer id, String name, String country1, String country2, LocalDate startDate, LocalDate endDate, LocalDate createtAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.country1 = country1;
        this.country2 = country2;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createtAt = createtAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "Benchmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country1='" + country1 + '\'' +
                ", country2='" + country2 + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createtAt=" + createtAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
