package com.santoflores.camarada.services;

import com.santoflores.camarada.models.ServiceInterest;
import com.santoflores.camarada.models.ServiceRequest;
import com.santoflores.camarada.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;
    private final ServiceInterestService serviceInterestService;

    public ServiceRequestService(ServiceRequestRepository repository, ServiceInterestService serviceInterestService) {
        this.repository = repository;
        this.serviceInterestService = serviceInterestService;
    }

    public List<ServiceRequest> findAll() {
        return repository.findAll();
    }
    
    public List<ServiceRequest> findByProfession(Long professionId) {
        return repository.findByProfessions_Id(professionId);
    }

    public List<ServiceRequest> findByProfessionsIn(List<Long> professionsId) {
        return repository.findByProfessions_IdIn(professionsId);
    }

    public List<ServiceRequest> findByProfessionIdAndNotAcceptedByProviderId(Long providerId) {
        
        List<Long> serviceRequests = serviceInterestService.findByProviderId(providerId).stream().map(it -> it.getServiceRequest().getId()).toList();
        
        repository.findByProfessions_Id(providerId);
    

    }

    
}