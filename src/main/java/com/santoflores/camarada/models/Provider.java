package com.santoflores.camarada.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.santoflores.camarada.enums.Plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers")
public class Provider {

    @Id
    private Long userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    @Column(name = "plan_expires_at")
    private LocalDateTime planExpiresAt;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    private String bio;

    private Short experience;

    private Boolean verified;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}