package com.assessment.task.backend.model.rs;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FeeRs {

    private Integer id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal fee;
}
