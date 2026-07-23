package com.santoflores.camarada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
    
    boolean existsByPhone(String phone);
}
