package com.santoflores.camarada.dtos.job;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class JobRequest {

    private Long providerId;

    private Long serviceId;

    private String title;

    private String description;

    private BigDecimal estimatedPrice;

}
