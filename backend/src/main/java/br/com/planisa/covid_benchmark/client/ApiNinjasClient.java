package br.com.planisa.covid_benchmark.client;

import br.com.planisa.covid_benchmark.model.external.ApiClientData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiNinjasClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    public ApiNinjasClient(
            RestTemplate restTemplate,
            @Value("${api.client.base-url}") String baseUrl,
            @Value("${api.client.api-key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ApiClientData[] getCovidData(String country, String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = baseUrl + "/v1/covid19?country={country}&type={type}";

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ApiClientData[].class,
                country,
                type
        ).getBody();
    }

}
