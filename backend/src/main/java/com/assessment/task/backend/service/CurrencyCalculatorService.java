package com.assessment.task.backend.service;

import com.assessment.task.backend.exception.NotFoundException;
import com.assessment.task.backend.exchangeapi.IExchangeApiClient;
import com.assessment.task.backend.exchangeapi.model.rs.ExchangeRateRs;
import com.assessment.task.backend.model.rs.CalculatorRs;
import com.assessment.task.backend.repo.FeeRepository;
import com.assessment.task.backend.repo.entity.FeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyCalculatorService implements ICurrencyCalculatorService {

    private final static int AMOUNT_SCALE = 2;

    private final BigDecimal DEFAULT_RATE;

    private final IExchangeApiClient exchangeApiClient;
    private final FeeRepository feeRepository;

    @Autowired
    public CurrencyCalculatorService(IExchangeApiClient exchangeApiClient,
                                     FeeRepository feeRepository,
                                     @Value("${currencies.rate.default}") BigDecimal defaultRate) {
        this.exchangeApiClient = exchangeApiClient;
        this.feeRepository = feeRepository;
        this.DEFAULT_RATE = defaultRate;
    }

    @Override
    public CalculatorRs exchange(String fromCurrency, String toCurrency, BigDecimal amount) {
        FeeEntity feeEntity = feeRepository.findByFromAndTo(fromCurrency, toCurrency);
        ExchangeRateRs exchangeRateRs = exchangeApiClient.getExchangeRate(fromCurrency, toCurrency);

        BigDecimal fee = feeEntity == null ? DEFAULT_RATE : amount.multiply(feeEntity.getPercent());
        BigDecimal rate = exchangeRateRs.getRates().get(toCurrency);

        if (rate == null) {
            throw new NotFoundException("Exchange rate not found");
        }

        BigDecimal result = amount.subtract(fee).multiply(rate);
        result = result.setScale(AMOUNT_SCALE, RoundingMode.HALF_DOWN); // round down to 2 decimals

        return CalculatorRs.builder()
                .amount(result)
                .currency(toCurrency)
                .build();
    }
}
