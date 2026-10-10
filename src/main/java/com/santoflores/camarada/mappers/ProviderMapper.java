package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.provider.ProviderReqDto;
import com.santoflores.camarada.dtos.provider.ProviderResDto;
import com.santoflores.camarada.models.Provider;

@Component
public class ProviderMapper {
    
    public ProviderResDto toResponse(Provider provider) {
        return new ProviderResDto(
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

    public Provider toModel(ProviderReqDto provider) {
        return Provider.builder()
        .bio(provider.bio())
        .cpfCnpj(provider.cpfCnpj())
        .experience(provider.experience())
        .build();
    }

}
