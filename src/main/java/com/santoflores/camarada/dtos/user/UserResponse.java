package com.santoflores.camarada.dtos.user;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String photoUrl;

}