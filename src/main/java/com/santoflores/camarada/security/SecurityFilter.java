package com.santoflores.camarada.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santoflores.camarada.repositories.UserRepository;

import java.io.IOException;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {        
        
        var token = this.recoverToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String phone = tokenService.validateToken(token);

            UserDetails user = userRepository.findByPhone(phone)
                .orElse(null);

            if (user == null) {
                sendUnauthorized(response);
                return;                
            }

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            sendUnauthorized(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }

    private void sendUnauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(
            response.getWriter(),
            Map.of(
                "status", 401,
                "code", "SESSION_INVALID",
                "message", "Sua sessão expirou."
            )
        );
    }
}