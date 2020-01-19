package com.assessment.task.backend.repo;

import com.assessment.task.backend.repo.entity.FeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepository extends JpaRepository<FeeEntity, Integer> {

    Page<FeeEntity> findAllByOrderByCreatedAsc(Pageable pageable);

    FeeEntity findByFromAndTo(String fromCurrency, String toCurrency);
}
