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

import com.santoflores.camarada.dtos.job.JobResponse;
import com.santoflores.camarada.mappers.JobMapper;
import com.santoflores.camarada.models.Job;
import com.santoflores.camarada.services.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService service;
    private final JobMapper jobMapper;

    @PostMapping
    public ResponseEntity<JobResponse> create(
            @RequestBody Job job){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobMapper.toResponse(service.save(job)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> findById(@PathVariable Long id){

        return ResponseEntity.ok(jobMapper.toResponse(service.findById(id)));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<JobResponse>> findByClient(
            @PathVariable Long id){

        return ResponseEntity.ok(
                service.findByClient(id).stream()
                .map(it -> jobMapper.toResponse((it))).toList());
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<List<JobResponse>> findByProvider(
            @PathVariable Long id){

        return ResponseEntity.ok(
            service.findByProvider(id).stream()
            .map(it -> jobMapper.toResponse(it)).toList());
    }
}