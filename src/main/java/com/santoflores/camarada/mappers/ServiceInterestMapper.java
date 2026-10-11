package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.serviceInterest.ServiceInterestReqDto;
import com.santoflores.camarada.dtos.serviceInterest.ServiceInterestResDto;
import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.models.ServiceInterest;
import com.santoflores.camarada.models.ServiceRequest;

@Component 
public class ServiceInterestMapper implements Mapper<ServiceInterest, ServiceInterestReqDto, ServiceInterestResDto> {
    @Override
    public ServiceInterest toModel(ServiceInterestReqDto dto) {
        return ServiceInterest.builder()
            .status(dto.status())
            .provider(Provider.builder().userId(dto.provider()).build())
            .serviceRequest(ServiceRequest.builder().id(dto.serviceRequest()).build())
            .build();
    }

    @Override
    public ServiceInterestResDto fromModel(ServiceInterest model) {
        return new ServiceInterestResDto(
            model.getId(),
            null,
            null,
            model.getStatus()
        );
    }
}
