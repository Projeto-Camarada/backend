package com.santoflores.camarada.dtos.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReviewRequest {

    @Min(1)
    @Max(5)
    private Byte rating;

    private String comment;

}
