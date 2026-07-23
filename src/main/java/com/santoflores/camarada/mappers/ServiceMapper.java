package com.santoflores.camarada.mappers;


import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.service.ServiceResponse;
import com.santoflores.camarada.models.Service;

@Component
public class ServiceMapper {
    
    public ServiceResponse toResponse(Service service) {
        return new ServiceResponse(
            service.getId(),
            service.getName(),
            service.getDescription()
        );
    }

}
