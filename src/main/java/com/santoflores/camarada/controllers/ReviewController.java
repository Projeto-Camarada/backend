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

import com.santoflores.camarada.dtos.review.ReviewResponse;
import com.santoflores.camarada.mappers.ReviewMapper;
import com.santoflores.camarada.models.Review;
import com.santoflores.camarada.services.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(
            @RequestBody Review review){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewMapper.toResponse(service.save(review)));
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<List<ReviewResponse>> findByProvider(
            @PathVariable Long id){

        return ResponseEntity.ok(service.findByProvider(id).stream()
            .map(it -> reviewMapper.toResponse(it)).toList());
    }
}