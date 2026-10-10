package com.santoflores.camarada.dtos.job;

import java.math.BigDecimal;

import lombok.Data;

public record Interest(
    Long id,

    @NotNull
    ServiceRequest serviceRequest,

    @NotNull
    Provider provider,

    @NotNull
    Status status
) {
    public ServiceRequestInterest toModel() { 
        return new ServiceRequestInterest( 
            null, 
            serviceRequest, 
            provider, 
            status 
        ); 
    }

    public static Interest fromModel(ServiceRequestInterest model) {
        return new Interest(
                model.getId(),
                model.getRequest().getId(),
                model.getProvider().getUserId(),
                model.getStatus()
        );
    }
}
