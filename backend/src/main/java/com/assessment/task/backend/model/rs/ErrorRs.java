package com.assessment.task.backend.model.rs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRs {

    private String message;
    private Integer status;
}
