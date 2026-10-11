package com.santoflores.camarada.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santoflores.camarada.dtos.auth.AuthResetPasswordReqDto;
import com.santoflores.camarada.dtos.auth.AuthResetPasswordResDto;
import com.santoflores.camarada.dtos.auth.AuthResponse;
import com.santoflores.camarada.dtos.auth.ForgotPasswordVerifyReqDto;
import com.santoflores.camarada.dtos.auth.LoginReqDto;
import com.santoflores.camarada.dtos.auth.RegisterRequest;
import com.santoflores.camarada.dtos.user.UserResetPasswordResDto;
import com.santoflores.camarada.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginReqDto request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/forgot-password/provider/verify")
    public ResponseEntity<Void> verifyDocuments(
            @RequestBody @Valid ForgotPasswordVerifyReqDto requestDto) {

        authService.verifyDocuments(requestDto);            
        
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResetPasswordResDto> resetPassword(@RequestBody @Valid AuthResetPasswordReqDto request) {
        return ResponseEntity.status(200).body(authService.resetPassword(request));
    }

}