package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.provider.ProviderResponse;
import com.santoflores.camarada.models.Provider;

@Component
public class ProviderMapper {
    
    public ProviderResponse toResponse(Provider provider) {
        return new ProviderResponse(
            provider.getUserId(),
            provider.getPlan(),
            provider.getPlanExpiresAt(),
            provider.getCpfCnpj(),
            provider.getBio(),
            provider.getUser().getName(),
            provider.getUser().getPhotoUrl(),
            provider.getVerified(),
            provider.getExperience()
        );
    }

}
