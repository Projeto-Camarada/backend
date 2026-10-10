package com.santoflores.camarada.dtos.serviceInterest;

import java.math.BigDecimal;

import com.santoflores.camarada.dtos.provider.ProviderResDto;
import com.santoflores.camarada.dtos.service.ServiceRequest;
import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestResDto;
import com.santoflores.camarada.models.ServiceInterest.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record ServiceInterestResDto(
    Long id,
    ServiceRequestResDto serviceRequest,
    ProviderResDto provider,
    Status status
) {}
