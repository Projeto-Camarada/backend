package com.santoflores.camarada.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    
    public UserAlreadyExistsException() {
        super("Usuário já cadastrado");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(UserAlreadyExistsBy existsBy) {
        super(switch (existsBy) {
            case EMAIL -> "Email já Cadastrado";
            case PHONE -> "Telefone já cadastrado";
        });
    }


    public enum UserAlreadyExistsBy {
        EMAIL,
        PHONE
    }
}

