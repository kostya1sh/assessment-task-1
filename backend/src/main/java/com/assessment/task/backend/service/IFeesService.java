package com.assessment.task.backend.service;

import com.assessment.task.backend.model.rs.FeesRs;

import java.math.BigDecimal;

public interface IFeesService {

    FeesRs getFees(int page, int count);

    void addFee(String from, String to, BigDecimal percent);

    void removeFee(Integer id);
}
