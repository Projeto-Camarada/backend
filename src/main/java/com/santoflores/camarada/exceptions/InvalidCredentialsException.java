package com.santoflores.camarada.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    
    public InvalidCredentialsException() {
        super("Dados inválidos");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
