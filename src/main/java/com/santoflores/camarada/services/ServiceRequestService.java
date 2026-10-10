package com.santoflores.camarada.services;

import com.santoflores.camarada.models.ProviderProfession;
import com.santoflores.camarada.models.ServiceInterest;
import com.santoflores.camarada.models.ServiceRequest;
import com.santoflores.camarada.repositories.ServiceRequestRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor 
public class ServiceRequestService {

    private final ServiceRequestRepository repository;
    private final ServiceInterestService serviceInterestService;
    private final ProviderProfessionService providerProfessionService;

    public List<ServiceRequest> findAll() {
        return repository.findAll();
    }
    
    public List<ServiceRequest> findByProfession(Long professionId) {
        return repository.findByProfessions_Id(professionId);
    }

    public List<ServiceRequest> findByProfessionsIn(List<Long> professionsId) {
        return repository.findByProfessions_IdIn(professionsId);
    }

    public List<ServiceRequest> findAvailableRequest(Long providerId) {
        
        List<Long> professionIds = providerProfessionService
            .findByProvider(providerId)
                .stream()
                    .map(it -> it.getProfession().getId())
                        .toList(); 

        List<ServiceRequest> requests = repository.findByProfessions_IdIn(professionIds); 
        
        List<Long> serviceRequests = serviceInterestService.findByProviderId(providerId)
            .stream().map(it -> it.getServiceRequest().getId()).toList();


        return requests.stream().filter(it -> !serviceRequests.contains(it.getId())).toList();
    }

    
}