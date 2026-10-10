package com.santoflores.camarada.services;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.ServiceInterest;
import com.santoflores.camarada.repositories.ServiceInterestRepository;

import java.util.List;

@Service
public class ServiceInterestService {

    private final ServiceInterestRepository repository;

    public ServiceInterestService(ServiceInterestRepository repository) {
        this.repository = repository;
    }

    public List<ServiceInterest> findByRequestId(Long requestId) {
        return repository.findByRequest_Id(requestId);
    }

    public List<ServiceInterest> findByProviderId(Long providerId) {
        return repository.findByProvider_Id(providerId);
    }

    public ServiceInterest create(ServiceInterest interest) {
        return repository.save(interest);
    }

    
}