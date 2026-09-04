package com.santoflores.camarada.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.santoflores.camarada.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santoflores.camarada.dtos.auth.LoginRequest;
import com.santoflores.camarada.dtos.auth.AuthResponse;
import com.santoflores.camarada.dtos.auth.RegisterRequest;
import com.santoflores.camarada.exceptions.EmailAlreadyExistsException;
import com.santoflores.camarada.exceptions.InvalidCredentialsException;
import com.santoflores.camarada.mappers.AuthMapper;
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


    public AuthResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            request.phone(), 
            request.password()
        );

        try {
            Authentication auth = authenticationManager.authenticate(authentication);
    
            User user = (User) auth.getPrincipal();
            

            String token = tokenService.generationToken(user);
        
            return new AuthResponse(token);
            
        } catch (BadCredentialsException e) {

            throw new InvalidCredentialsException("Telefone ou senha inválidos.");

        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            throw e;
        }

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

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.phone())) {
            throw new RuntimeException("Telefone já cadastrado.");
        }

        String email = request.email();

        if (email != null && !email.isBlank()) {
            email = email.trim().toLowerCase();

            if (userRepository.existsByEmail(email)) {
                throw new EmailAlreadyExistsException("E-mail já cadastrado");
            }
        } else {
            email = null;
        }

        User user = authMapper.toModel(request, passwordEncoder);
        user.setEmail(email);
        
        try {
            user = userRepository.save(user);

            String token = tokenService.generationToken(user);

            return new AuthResponse(token);

        } catch (DataIntegrityViolationException e) {
            //POR ENQUANTO DEIXAR ASSIM
            throw new InvalidCredentialsException("Dados inválidos.");

        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            throw e;
        }

    }
}
