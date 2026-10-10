package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestReqDto;
import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestResDto;
import com.santoflores.camarada.models.ServiceRequest;

@Component 
public class ServiceRequestMapper implements Mapper<ServiceRequest, ServiceRequestReqDto, ServiceRequestResDto> {
    @Override
    public ServiceRequest toModel(ServiceRequestReqDto dto) {
        return ServiceRequest.builder()
            .description(dto.description())
            .estimatedDurationHours(dto.estimatedDurationHours())
            .estimatedPrice(dto.estimatedPrice())
            .status(dto.status())
            .title(dto.title())
            .build();
    }


    @Override
    public ServiceRequestResDto fromModel(ServiceRequest model) {
        return new ServiceRequestResDto(
            model.getId(),
            null,
            model.getTitle(),
            model.getDescription(),
            model.getEstimatedPrice(),
            model.getEstimatedDurationHours(),
            model.getStatus(),
            model.getCreatedAt(),
            null
        );
    }
}
