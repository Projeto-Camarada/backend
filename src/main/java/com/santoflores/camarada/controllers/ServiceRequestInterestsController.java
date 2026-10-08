package com.santoflores.camarada.controllers;

import com.santoflores.camarada.dtos.ServiceRequestResponseDTO;
import com.santoflores.camarada.services.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequestResponseDTO>> findAll() {

        return ResponseEntity.ok(
                serviceRequestService.findAll()
        );
    }
}