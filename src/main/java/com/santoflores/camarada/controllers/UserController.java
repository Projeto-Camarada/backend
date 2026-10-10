package com.santoflores.camarada.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santoflores.camarada.dtos.user.UserResDto;
import com.santoflores.camarada.mappers.UserMapper;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    } 

    @GetMapping("/{id}")
    public ResponseEntity<UserResDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.fromModel(service.findById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResDto> getMe(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(mapper.fromModel(service.findById(user.getId())));
    }

    @GetMapping
    public ResponseEntity<List<UserResDto>> findAll(){
        return ResponseEntity.ok(service.findAll().stream()
            .map(it -> mapper.fromModel(it)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResDto> update(
            @PathVariable Long id,
            @RequestBody User user){

        user.setId(id);
        return ResponseEntity.ok(mapper.fromModel(service.save(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}