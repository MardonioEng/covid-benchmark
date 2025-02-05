package br.com.planisa.covid_benchmark.service;

import br.com.planisa.covid_benchmark.client.ApiNinjasClient;
import br.com.planisa.covid_benchmark.model.external.ApiClientData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiClientService {

    private final ApiNinjasClient apiNinjasClient;

    public ApiClientService(ApiNinjasClient apiNinjasClient) {
        this.apiNinjasClient = apiNinjasClient;
    }


    public ApiClientData[] getCovidData(String country) {
        ApiClientData[] casesData = apiNinjasClient.getCovidData(country, "cases");
        ApiClientData[] deathsData = apiNinjasClient.getCovidData(country, "deaths");

        Map<String, ApiClientData> dataMap = new HashMap<>();

        for (ApiClientData caseEntry : casesData) {
            dataMap.put(caseEntry.getRegion(), caseEntry);
        }

        for (ApiClientData deathEntry : deathsData) {
            dataMap.computeIfPresent(deathEntry.getRegion(), (key, existingData) -> {
                existingData.setDeaths(deathEntry.getDeaths());
                return existingData;
            });
        }

        return dataMap.values().toArray(new ApiClientData[0]);
    }
}
