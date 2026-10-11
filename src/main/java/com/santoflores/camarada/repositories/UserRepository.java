package com.santoflores.camarada.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    Optional<User> findByIdAndPhone(Long id, String phone);

    Optional<User> findByIdAndEmailIgnoreCaseOrPhone(Long id, String email, String phone);

    Optional<User> findByEmailIgnoreCaseOrPhone(String email, String phone);
    
    Optional<User> findByPhone(String phone);

    boolean existsByEmail(String email);
    
    boolean existsByPhone(String phone);
}
