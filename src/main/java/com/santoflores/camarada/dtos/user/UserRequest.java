package com.santoflores.camarada.dtos.user;

import lombok.Data;

@Data
public class UserRequest {

    private String name;

    private String phone;

    private String email;

    private String password;

    private String photoUrl;

}