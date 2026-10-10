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

import com.santoflores.camarada.dtos.profession.ProfessionReqDto;
import com.santoflores.camarada.dtos.profession.ProfessionResDto;
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
    public ResponseEntity<ProfessionResDto> create(
            @RequestBody ProfessionReqDto profession){

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
    public ResponseEntity<ProfessionResDto> findById(@PathVariable Long id){

        return ResponseEntity.ok(professionMapper.toResponse(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionResDto>> findAll(){
        return ResponseEntity.ok(
            service.findAll().stream()
            .map(it -> professionMapper.toResponse(it)).toList());
    }
}