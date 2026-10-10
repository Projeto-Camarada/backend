package com.santoflores.camarada.services;

import com.santoflores.camarada.models.ServiceRequestInterests;
import com.santoflores.camarada.repositories.ServiceRequestInterestsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceRequestInterestsService {

    private final ServiceRequestInterestsRepository repository;

    public ServiceRequestInterestsService(ServiceRequestInterestsRepository repository) {
        this.repository = repository;
    }

    public List<ServiceRequestInterests> findByRequest(Long requestId) {
        return repository.findByRequest_Id(requestId);
    }

    public ServiceRequestInterests create(ServiceRequestInterests interest) {
        return repository.save(serviceRequestInterests);
    }

    
}