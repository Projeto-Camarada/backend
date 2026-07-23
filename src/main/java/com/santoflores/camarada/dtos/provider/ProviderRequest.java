package com.santoflores.camarada.dtos.provider;

import lombok.Data;

@Data
public class ProviderRequest {

    private String cpfCnpj;

    private String bio;

    private Short experience;

}