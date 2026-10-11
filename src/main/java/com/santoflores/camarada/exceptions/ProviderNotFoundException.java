package com.santoflores.camarada.exceptions;

public class ProviderNotFoundException extends RuntimeException {
    public ProviderNotFoundException() {
        super("Profissional não encontrado");
    }

    public ProviderNotFoundException(String message) {
        super(message);
    }
}
