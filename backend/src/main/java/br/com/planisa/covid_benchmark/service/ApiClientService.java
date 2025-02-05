package br.com.planisa.covid_benchmark.service;

import br.com.planisa.covid_benchmark.client.ApiNinjasClient;
import br.com.planisa.covid_benchmark.model.external.ApiClientData;
import org.springframework.stereotype.Service;

@Service
public class ApiClientService {

    private final ApiNinjasClient apiNinjasClient;

    public ApiClientService(ApiNinjasClient apiNinjasClient) {
        this.apiNinjasClient = apiNinjasClient;
    }

    public ApiClientData[] getCovidCases(String country) {
        return apiNinjasClient.getCovidData(country, "cases");
    }

    public ApiClientData[] getCovidDeaths(String country) {
        return apiNinjasClient.getCovidData(country, "deaths");
    }
}
