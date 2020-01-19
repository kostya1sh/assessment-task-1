package com.assessment.task.backend.repo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FEE")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class FeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "from_currency", nullable = false)
    private String from;

    @Basic
    @Column(name = "to_currency", nullable = false)
    private String to;

    @Basic
    @Column(name = "percent", nullable = false)
    private BigDecimal percent;

    @Basic
    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime created;
}
