package br.com.planisa.covid_benchmark.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ApiClientData {

    private String country;
    private String region;

    @JsonProperty(value = "cases", required = false)
    private Map<String, DailyData> cases;

    @JsonProperty(value = "deaths", required = false)
    private Map<String, DailyData> deaths;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, DailyData> getCases() {
        return cases;
    }

    public void setCases(Map<String, DailyData> cases) {
        this.cases = cases;
    }

    public Map<String, DailyData> getDeaths() {
        return deaths;
    }

    public void setDeaths(Map<String, DailyData> deaths) {
        this.deaths = deaths;
    }

}
