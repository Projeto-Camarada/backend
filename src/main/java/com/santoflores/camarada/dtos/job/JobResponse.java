package com.santoflores.camarada.dtos.job;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.santoflores.camarada.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobResponse {

    private Long id;

    private String title;

    private String description;

    private JobStatus status;

    private BigDecimal estimatedPrice;

    private BigDecimal finalPrice;

    private LocalDateTime requestedAt;

    private Short estimatedDurationHours;

    private Short actualDurationHours;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

}
