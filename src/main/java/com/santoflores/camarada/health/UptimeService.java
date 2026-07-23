package com.santoflores.camarada.health;

import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class UptimeService {

    private final Instant started = Instant.now();

    public Duration getUptime() {
        return Duration.between(started, Instant.now());
    }

}
