package com.santoflores.camarada.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santoflores.camarada.exceptions.UserNotFoundException;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        try {
            User user = repository.findByPhone(phone).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado.")
            );

            return user;

        } catch (Exception e) {

            e.printStackTrace();

            throw e;
        }
    }

    public User save(User user){
        return repository.save(user);
    }

    public User findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    public User findByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    public User resetPassword(User user) {
        User userToUp = repository.findById(user.getId())
            .orElseThrow(UserNotFoundException::new);
            
        userToUp.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return repository.save(userToUp);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
