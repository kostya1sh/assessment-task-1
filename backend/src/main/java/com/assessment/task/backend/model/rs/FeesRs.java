package com.assessment.task.backend.model.rs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeesRs {

    private Integer page;
    private Integer totalPages;
    private List<FeeRs> fees;
}
