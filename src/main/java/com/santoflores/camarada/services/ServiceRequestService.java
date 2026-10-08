package com.santoflores.camarada.services;

import com.santoflores.camarada.dtos.ServiceRequestResponseDTO;
import com.santoflores.camarada.models.ServiceRequest;
import com.santoflores.camarada.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @Transactional(readOnly = true)
    public List<ServiceRequestResponseDTO> findAll() {

        List<ServiceRequest> requests =
                serviceRequestRepository.findAll();

        return requests.stream()
                .map(ServiceRequestResponseDTO::fromEntity)
                .toList();
    }

    
}