package com.assessment.task.backend.exchangeapi;

import com.assessment.task.backend.exchangeapi.model.rs.ExchangeRateRs;

import java.util.Collection;

public interface IExchangeApiClient {

    Collection<String> getAvailableCurrencies();

    ExchangeRateRs getExchangeRate(String base, String... symbols);

    ExchangeRateRs getExchangeRate(String base);
}
