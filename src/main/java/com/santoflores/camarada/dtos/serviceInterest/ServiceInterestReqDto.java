package com.santoflores.camarada.dtos.serviceInterest;

import java.math.BigDecimal;

import com.santoflores.camarada.models.ServiceInterest.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record ServiceInterestReqDto(
    @NotNull 
    Long serviceRequest,

    @NotNull
    Long provider,

    @NotNull
    Status status
) {}
