package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.review.ReviewResponse;
import com.santoflores.camarada.models.Review;

@Component
public class ReviewMapper {
    
    public ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
            review.getId(),
            review.getRating(),
            review.getComment(),
            review.getUser().getName(),
            review.getCreatedAt()
        );
    }

}
