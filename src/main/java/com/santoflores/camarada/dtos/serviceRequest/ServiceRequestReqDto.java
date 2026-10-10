package com.santoflores.camarada.dtos.serviceRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.santoflores.camarada.models.ServiceRequest.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceRequestReqDto (
    @NotNull
    Long client,
    
    @NotBlank 
    String title,
    
    String description,
    
    @NotNull 
    BigDecimal estimatedPrice,
    
    @NotNull 
    Short estimatedDurationHours,
    
    @NotNull 
    Status status,
    
    @NotNull 
    List<Long> professions
) {}
