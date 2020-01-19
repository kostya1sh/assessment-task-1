package com.assessment.task.backend.model.rq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CalculatorRq {

    @NotEmpty
    private String fromCurrency;

    @NotEmpty
    private String toCurrency;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal amount;
}
