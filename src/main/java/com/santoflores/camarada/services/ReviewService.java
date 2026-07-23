package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.Review;
import com.santoflores.camarada.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;

    public Review save(Review review){
        return repository.save(review);
    }

    public List<Review> findByProvider(Long providerId){
        return repository.findByProviderUserId(providerId);
    }

}
