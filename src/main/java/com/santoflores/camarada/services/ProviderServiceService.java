package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.repositories.ProviderServiceRepository;

import com.santoflores.camarada.models.ProviderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderServiceService {

    private final ProviderServiceRepository repository;

    public ProviderService save(ProviderService providerService){
        return repository.save(providerService);
    }

    public List<ProviderService> findByProvider(Long providerId){
        return repository.findByProviderUserId(providerId);
    }

    public List<ProviderService> findByService(Long serviceId){
        return repository.findByServiceId(serviceId);
    }

}
