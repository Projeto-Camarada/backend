package com.santoflores.camarada.model;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
 
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull 
    private String email;
    @NotNull
    private String password;
    @NotNull 
    private String phone;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }

}
