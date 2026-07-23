package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProviderUserId(Long providerId);

}