package com.santoflores.camarada.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.santoflores.camarada.enums.JobStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Column(name = "estimated_price")
    private BigDecimal estimatedPrice;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @Column(name = "estimated_duration_hours")
    private Short estimatedDurationHours;

    @Column(name = "actual_duration_hours")
    private Short actualDurationHours;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}