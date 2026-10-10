package com.santoflores.camarada.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santoflores.camarada.dtos.serviceInterest.ServiceInterestReqDto;
import com.santoflores.camarada.dtos.serviceInterest.ServiceInterestResDto;
import com.santoflores.camarada.mappers.ServiceInterestMapper;
import com.santoflores.camarada.services.ServiceInterestService;

import java.util.List;

@RestController

@RequestMapping("/service-interests")
public class ServiceInterestController {
    
    private final ServiceInterestMapper mapper;
    private final ServiceInterestService service;

    public ServiceInterestController(ServiceInterestService service, ServiceInterestMapper mapper) {
        this.service = service;
        this.mapper = mapper; 
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<List<ServiceInterestResDto>> findByRequestId(@PathVariable Long id) {
        return ResponseEntity.ok(
            service.findByRequestId(id).stream()
                .map(it -> mapper.fromModel(it))
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<ServiceInterestResDto> save(@RequestBody ServiceInterestReqDto interestReq) {
        return ResponseEntity.ok(
            mapper.fromModel(
                service.create(mapper.toModel(interestReq))
            )
        );
    }

}