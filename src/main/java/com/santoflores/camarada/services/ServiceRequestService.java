package com.santoflores.camarada.services;

import com.santoflores.camarada.models.ServiceRequest;
import com.santoflores.camarada.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }
    
    public List<ServiceRequest> findByProfession(Long professionId) {
        return serviceRequestRepository.findByProfessions_Id(professionId);
    }

    public List<ServiceRequest> findByProfessionsIn(List<Long> professionsId) {
        return serviceRequestRepository.findByProfessions_IdIn(professionsId);
    }

    
}