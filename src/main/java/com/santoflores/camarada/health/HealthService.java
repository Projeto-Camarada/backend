package com.santoflores.camarada.health;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.santoflores.camarada.health.dto.HealthResponse;
import com.santoflores.camarada.health.dto.MemoryInfo;
import com.santoflores.camarada.health.dto.RequestInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final JdbcTemplate jdbcTemplate;
    private final UptimeService uptimeService;
    private final RequestCounterFilter requestCounterFilter;

    public HealthResponse health() {

        boolean database = checkDatabase();

        Runtime runtime = Runtime.getRuntime();

        long used =
                (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;

        long max =
                runtime.maxMemory() / 1024 / 1024;

        return new HealthResponse(

            database ? "UP" : "DOWN",

            "1.0.0",

            uptimeService.getUptime().toString(),

            database,

            new MemoryInfo(used, max),

            new RequestInfo(

                    requestCounterFilter.getTotalRequests(),

                    requestCounterFilter.getErrorRequests()

            ),

            LocalDateTime.now()

        );
    }

    private boolean checkDatabase() {

        try {

            jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}