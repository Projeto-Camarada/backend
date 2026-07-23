package com.santoflores.camarada.health;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestCounterFilter extends OncePerRequestFilter {

    private final AtomicLong totalRequests = new AtomicLong();
    private final AtomicLong errorRequests = new AtomicLong();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        totalRequests.incrementAndGet();

        filterChain.doFilter(request, response);

        if (response.getStatus() >= 400) {
            errorRequests.incrementAndGet();
        }
    }

    public long getTotalRequests() {
        return totalRequests.get();
    }

    public long getErrorRequests() {
        return errorRequests.get();
    }
}