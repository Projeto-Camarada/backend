package com.santoflores.camarada.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
