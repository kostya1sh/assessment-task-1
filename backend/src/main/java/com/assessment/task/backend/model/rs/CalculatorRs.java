package com.assessment.task.backend.model.rs;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalculatorRs {

    private String currency;
    private BigDecimal amount;
}
