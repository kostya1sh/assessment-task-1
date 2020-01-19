package com.assessment.task.backend.service;

import com.assessment.task.backend.model.rs.FeeRs;
import com.assessment.task.backend.model.rs.FeesRs;
import com.assessment.task.backend.repo.FeeRepository;
import com.assessment.task.backend.repo.entity.FeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeesService implements IFeesService {

    private final FeeRepository feeRepository;

    @Autowired
    public FeesService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public FeesRs getFees(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<FeeEntity> feesPage = feeRepository.findAllByOrderByCreatedAsc(pageable);

        List<FeeRs> feeRsList = feesPage.stream()
                .map(e -> FeeRs.builder()
                        .id(e.getId())
                        .fee(e.getPercent())
                        .fromCurrency(e.getFrom())
                        .toCurrency(e.getTo())
                        .build())
                .collect(Collectors.toList());

        return FeesRs.builder()
                .totalPages(feesPage.getTotalPages())
                .page(feesPage.getNumber())
                .fees(feeRsList)
                .build();
    }

    @Override
    public void addFee(String from, String to, BigDecimal percent) {
        FeeEntity feeEntity = new FeeEntity();
        feeEntity.setFrom(from);
        feeEntity.setTo(to);
        feeEntity.setPercent(percent);

        feeRepository.save(feeEntity);
    }

    @Override
    public void removeFee(Integer id) {
        feeRepository.deleteById(id);
    }
}
