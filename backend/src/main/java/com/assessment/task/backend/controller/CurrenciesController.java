package com.assessment.task.backend.controller;

import com.assessment.task.backend.exchangeapi.IExchangeApiClient;
import com.assessment.task.backend.exchangeapi.model.rs.ExchangeRateRs;
import com.assessment.task.backend.model.rq.CalculatorRq;
import com.assessment.task.backend.model.rs.CalculatorRs;
import com.assessment.task.backend.model.rs.CurrenciesRs;
import com.assessment.task.backend.service.ICurrencyCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/currencies")
@CrossOrigin(origins = "http://localhost:8080")
public class CurrenciesController {

    private final IExchangeApiClient exchangeApiClient;
    private final ICurrencyCalculatorService currencyCalculatorService;

    @Autowired
    public CurrenciesController(IExchangeApiClient exchangeApiClient,
                                ICurrencyCalculatorService currencyCalculatorService) {
        this.exchangeApiClient = exchangeApiClient;
        this.currencyCalculatorService = currencyCalculatorService;
    }

    @GetMapping("")
    public ResponseEntity<CurrenciesRs> getCurrencies() {
        Collection<String> currencies = exchangeApiClient.getAvailableCurrencies();
        CurrenciesRs currenciesRs = new CurrenciesRs();
        currenciesRs.setCurrencies(currencies);
        return new ResponseEntity<>(currenciesRs, HttpStatus.OK);
    }

    @GetMapping("/exchange-rate/{from}")
    public ResponseEntity<ExchangeRateRs> getExchangeRate(@PathVariable String from) {
        ExchangeRateRs exchangeRate = exchangeApiClient.getExchangeRate(from);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    @GetMapping("/exchange-rate/{from}/{to}")
    public ResponseEntity<ExchangeRateRs> getExchangeRate(@PathVariable String from,
                                                          @PathVariable String to) {
        ExchangeRateRs exchangeRate = exchangeApiClient.getExchangeRate(from, to);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    @PostMapping("/calculator")
    public ResponseEntity<CalculatorRs> exchange(@RequestBody @Valid CalculatorRq calculatorRq) {
        CalculatorRs rs = currencyCalculatorService.exchange(calculatorRq.getFromCurrency(),
                calculatorRq.getToCurrency(), calculatorRq.getAmount());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
