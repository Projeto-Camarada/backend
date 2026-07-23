package com.santoflores.camarada.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.santoflores.camarada.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santoflores.camarada.dtos.auth.LoginRequest;
import com.santoflores.camarada.dtos.auth.LoginResponse;
import com.santoflores.camarada.dtos.auth.RegisterRequest;
import com.santoflores.camarada.dtos.user.UserResponse;
import com.santoflores.camarada.mappers.AuthMapper;
import com.santoflores.camarada.mappers.UserMapper;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;


    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            request.getEmail(), 
            request.getPassword()
        );

        Authentication auth = authenticationManager.authenticate(authentication);

        User user = (User) auth.getPrincipal();

        String token = tokenService.generationToken(user);
    
        return new LoginResponse(token);
    }

    public User getUserInfo(String token) {
        Long userId;

        try {
            token = token.replace("Bearer ", "");
            userId = tokenService.getUserIdFromToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido ou expirado.", e);
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return user; 
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = authMapper.toModel(request, passwordEncoder);

        return userRepository.save(user);
    }
}
