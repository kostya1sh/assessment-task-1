package com.assessment.task.backend.model.rs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class CurrenciesRs {

    private Collection<String> currencies;
}
