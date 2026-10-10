package com.santoflores.camarada.controllers;

import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestDto;
import com.santoflores.camarada.mappers.ServiceRequestMapper;
import com.santoflores.camarada.services.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService service;
    private final ServiceRequestMapper mapper;

    public ServiceRequestController(ServiceRequestService service, ServiceRequestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequestDto>> findAll() {
        return ResponseEntity.ok(
            service.findAll().stream()
                .map(it -> mapper.fromModel(it))
                .toList()
        );
    }

    @GetMapping("/profession/{professionId}")
    public ResponseEntity<List<ServiceRequestDto>> findByProfessionId(@PathVariable Long professionId) {
        return ResponseEntity.ok(
            service.findByProfession(professionId).stream()
                .map(it -> mapper.fromModel(it))
                .toList()
        );
    }
}