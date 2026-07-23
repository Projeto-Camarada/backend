package com.santoflores.camarada.dtos.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceResponse {

    private Long id;

    private String name;

    private String description;

}
