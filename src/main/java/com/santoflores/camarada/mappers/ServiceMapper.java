package com.santoflores.camarada.mappers;


import com.santoflores.camarada.dtos.service.ServiceResponse;
import com.santoflores.camarada.models.Service;

public class ServiceMapper {
    
    public ServiceResponse toResponse(Service service) {
        return new ServiceResponse(
            service.getId(),
            service.getName(),
            service.getDescription()
        );
    }

}
