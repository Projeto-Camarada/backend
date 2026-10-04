package com.santoflores.camarada.dtos;

import com.santoflores.camarada.models.ServiceRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ServiceRequestResponseDTO(
        Long id,
        Long clientId,
        String clientName,
        String title,
        String description,
        BigDecimal estimatedPrice,
        Short estimatedDurationHours,
        String status,
        LocalDateTime createdAt,
        List<ProfessionDTO> professions
) {

    public record ProfessionDTO(
            Long id,
            String name
    ) {}

    public static ServiceRequestResponseDTO fromEntity(ServiceRequest request) {

        List<ProfessionDTO> professions = request.getProfessions()
                .stream()
                .map(profession -> new ProfessionDTO(
                        profession.getId(),
                        profession.getName()
                ))
                .toList();

        return new ServiceRequestResponseDTO(
                request.getId(),
                request.getClient().getId(),
                request.getClient().getName(),
                request.getTitle(),
                request.getDescription(),
                request.getEstimatedPrice(),
                request.getEstimatedDurationHours(),
                request.getStatus().name(),
                request.getCreatedAt(),
                professions
        );
    }
}