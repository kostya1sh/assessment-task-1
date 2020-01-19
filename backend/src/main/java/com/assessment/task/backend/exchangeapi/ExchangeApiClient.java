package com.assessment.task.backend.exchangeapi;

import com.assessment.task.backend.exchangeapi.model.rs.ExchangeRateRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExchangeApiClient implements IExchangeApiClient {

    private static final String BASE_URL = "https://api.exchangeratesapi.io";

    @Override
    public Collection<String> getAvailableCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRateRs> response = restTemplate.getForEntity(BASE_URL + "/latest", ExchangeRateRs.class);
        ExchangeRateRs rs = response.getBody();

        if (rs == null) {
            return new ArrayList<>();
        }

        Set<String> currencies = rs.getRates().keySet();
        Set<String> resultSet = new HashSet<>(currencies);
        resultSet.add(rs.getBase());
        return resultSet;
    }

    @Override
    public ExchangeRateRs getExchangeRate(String base, String... symbols) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRateRs> response = restTemplate.getForEntity(
                BASE_URL + "/latest?base={base}&symbols={symbols}",
                ExchangeRateRs.class,
                base,
                symbols);

        return response.getBody();
    }

    @Override
    public ExchangeRateRs getExchangeRate(String base) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRateRs> response = restTemplate.getForEntity(
                BASE_URL + "/latest?base={base}",
                ExchangeRateRs.class,
                base);

        return response.getBody();
    }
}
