package com.santoflores.camarada.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santoflores.camarada.dtos.service.ServiceResponse;
import com.santoflores.camarada.mappers.ServiceMapper;
import com.santoflores.camarada.models.Service;
import com.santoflores.camarada.services.ServiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;
    private final ServiceMapper serviceMapper;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> findAll(){

        return ResponseEntity.ok(
                service.findAll().stream()
                .map(it -> serviceMapper.toResponse(it)).toList());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> create(
            @RequestBody Service request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serviceMapper.toResponse(service.save(request)));
    }
}