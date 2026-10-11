package com.santoflores.camarada.dtos.user;

import com.santoflores.camarada.models.User;

public record UserResetPasswordResDto(
    String phone
) {
    public UserResetPasswordResDto fromModel(User model) {
        return new UserResetPasswordResDto(model.getPhone());
    }

}
