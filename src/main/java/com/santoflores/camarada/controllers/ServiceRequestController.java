package com.santoflores.camarada.controllers;

import com.santoflores.camarada.dtos.serviceRequest.ServiceRequestResDto;
import com.santoflores.camarada.mappers.ServiceRequestMapper;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.services.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/provider/by/profession-and-not-accepted")
    public ResponseEntity<List<ServiceRequestResDto>> findByProfessionIdAndNotAcceptedByProviderId(Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();
      


        return ResponseEntity.ok(
            service.findByProfessionIdAndNotAcceptedByProviderId(usreId).stream()
                .map(it -> mapper.fromModel(it))
                .toList()
        );
    }
}