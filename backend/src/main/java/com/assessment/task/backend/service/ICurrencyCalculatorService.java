package com.assessment.task.backend.service;

import com.assessment.task.backend.model.rs.CalculatorRs;

import java.math.BigDecimal;

public interface ICurrencyCalculatorService {

    CalculatorRs exchange(String fromCurrency, String toCurrency, BigDecimal amount);
}
