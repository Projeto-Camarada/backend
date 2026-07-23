package com.santoflores.camarada.dtos.provider;

import lombok.Data;

@Data
public class ProviderSummaryResponse {

    private Long id;

    private String name;

    private String photoUrl;

    private Boolean verified;

    private Double rating;

}