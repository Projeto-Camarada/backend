package com.santoflores.camarada.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santoflores.camarada.dtos.profession.ProfessionRequest;
import com.santoflores.camarada.dtos.profession.ProfessionResponse;
import com.santoflores.camarada.mappers.ProfessionMapper;
import com.santoflores.camarada.services.ProfessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/professions")
@RequiredArgsConstructor
public class ProfessionController {

    private final ProfessionService service;
    private final ProfessionMapper professionMapper;

    @PostMapping
    public ResponseEntity<ProfessionResponse> create(
            @RequestBody ProfessionRequest profession){

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                professionMapper.toResponse(
                    service.save(
                        professionMapper.toModel(profession)
                    )
                )
            );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionResponse> findById(@PathVariable Long id){

        return ResponseEntity.ok(professionMapper.toResponse(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionResponse>> findAll(){
        return ResponseEntity.ok(
            service.findAll().stream()
            .map(it -> professionMapper.toResponse(it)).toList());
    }
}