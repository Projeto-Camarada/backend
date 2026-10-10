package com.santoflores.camarada.dtos.serviceRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.santoflores.camarada.models.Profession;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.models.ServiceRequest.Status;

public record ServiceRequestDto (
    Long id,
    User client,
    String title,
    String description,
    BigDecimal estimatedPrice,
    Short estimatedDurationHours,
    Status status,
    LocalDateTime createdAt,
    List<Profession> professions
) {}
