package com.santoflores.camarada.handlers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.santoflores.camarada.exceptions.DuplicatePhoneException;
import com.santoflores.camarada.exceptions.EmailAlreadyExistsException;
import com.santoflores.camarada.exceptions.InvalidCredentialsException;
import com.santoflores.camarada.exceptions.ProviderNotFoundException;
import com.santoflores.camarada.exceptions.UserAlreadyExistsException;
import com.santoflores.camarada.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GlobalExceptionResDto> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(404).body(new GlobalExceptionResDto(e.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<GlobalExceptionResDto> handleInvalidCredentials(InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GlobalExceptionResDto(e.getMessage(), "INVALID_CREDENTIALS"));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionResDto> handlerUserAlreadyExists(UserAlreadyExistsException e) {
        return ResponseEntity.status(409).body(new GlobalExceptionResDto(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Corpo da requisição inválido");
    }

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<GlobalExceptionResDto> handlerProviderNotFound(ProviderNotFoundException e) {
        return ResponseEntity.status(404).body(new GlobalExceptionResDto(e.getMessage()));
    } 



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {

        // System.out.println(ex.getLocalizedMessage());
        System.out.println(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "Ocorreu um erro interno." 
                ));
    }

    public record GlobalExceptionResDto(
        String message,
        String code
    ) {
        public GlobalExceptionResDto(String message) {
            this(message, null);
        }
    }

}
