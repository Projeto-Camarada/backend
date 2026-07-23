package com.santoflores.camarada.dtos.review;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponse {

    private Long id;

    private Byte rating;

    private String comment;

    private String userName;

    private LocalDateTime createdAt;

}
