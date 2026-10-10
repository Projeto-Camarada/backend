package com.santoflores.camarada.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santoflores.camarada.dtos.provider.ProviderReqDto;
import com.santoflores.camarada.dtos.provider.ProviderResDto;
import com.santoflores.camarada.dtos.user.UserResDto;
import com.santoflores.camarada.mappers.ProviderMapper;
import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.services.ProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService service;
    private final ProviderMapper providerMapper;

    @GetMapping
    public ResponseEntity<List<ProviderResDto>> findAll(){
        return ResponseEntity.ok(service.findAll().stream()
            .map(it -> providerMapper.toResponse(it)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(providerMapper.toResponse(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProviderResDto> save(
        @RequestBody ProviderReqDto provider,
        Authentication authentication
    ){

        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();
        
        Provider providerModel = providerMapper.toModel(provider); 

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                providerMapper.toResponse(
                    service.save(
                        providerModel, userId, provider.serviceIds()
                    )
                )
            );
    }
}