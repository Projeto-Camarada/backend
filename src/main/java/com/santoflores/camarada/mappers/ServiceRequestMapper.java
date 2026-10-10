package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestDto;
import com.santoflores.camarada.models.ServiceRequest;

@Component 
public class ServiceRequestMapper implements Mapper<ServiceRequest, ServiceRequestDto> {
    @Override
    public ServiceRequest toModel(ServiceRequestDto dto) {
        return ServiceRequest.builder()
            .client(dto.client())
            .description(dto.description())
            .estimatedDurationHours(dto.estimatedDurationHours())
            .estimatedPrice(dto.estimatedPrice())
            .status(dto.status())
            .title(dto.title())
            .build();
    }


    @Override
    public ServiceRequestDto fromModel(ServiceRequest model) {
        return new ServiceRequestDto(
            model.getId(),
            model.getClient(),
            model.getTitle(),
            model.getDescription(),
            model.getEstimatedPrice(),
            model.getEstimatedDurationHours(),
            model.getStatus(),
            model.getCreatedAt(),
            model.getProfessions()
        );
    }
}
