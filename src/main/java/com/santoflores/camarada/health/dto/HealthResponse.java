package com.santoflores.camarada.health.dto;

import java.time.LocalDateTime;

public record HealthResponse(

    String status,
    String version,
    String uptime,
    boolean database,

    MemoryInfo memory,

    RequestInfo requests,

    LocalDateTime timestamp

) {}
