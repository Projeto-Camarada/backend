package com.santoflores.camarada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
    
    boolean existsByPhone(String phone);
}
