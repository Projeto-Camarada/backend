package com.santoflores.camarada.controllers;

import com.santoflores.camarada.services.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/service-request-interests")
public class ServiceRequestInterestsController {

    private final ServiceRequestInterestsService service;
    private final Interest dto;

    public ServiceRequestInterestsController(ServiceRequestInterestsService service, Interest dto) {
        this.service = service;
        this.dto = dto; 
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<List<Interest>> findByRequest(@PathVariable Long id) {
        return ResponseEntity.ok(
            service.findByRequest(id).stream().map(it -> dto.fromModel(it)).toList()
        );
    }

    @PostMapping
    public ResponseEntity<Interest> save(@RequestBody Interest interest) {
        return ResponseEntity.ok(
            service.create(interest.toModel())
        );
    }

}