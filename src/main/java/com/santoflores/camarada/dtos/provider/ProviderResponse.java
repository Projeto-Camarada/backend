package com.santoflores.camarada.dtos.provider;

import lombok.Data;

@Data
public class ProviderResponse {

    private Long id;

    private String name;

    private String photoUrl;

    private String bio;

    private Boolean verified;

    private Short experience;

    private Double rating;

}