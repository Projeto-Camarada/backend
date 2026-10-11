package com.santoflores.camarada.exceptions;

public class DuplicatePhoneException extends RuntimeException{
    
    public DuplicatePhoneException() {
        super("Número já cadastrado");
    }

    public DuplicatePhoneException(String message) {
        super(message);
    }
}
