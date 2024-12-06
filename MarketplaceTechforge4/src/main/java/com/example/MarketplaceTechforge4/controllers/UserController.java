package com.example.MarketplaceTechforge4.controllers;
import com.example.MarketplaceTechforge4.domain.user.RequestUser;
import com.example.MarketplaceTechforge4.domain.user.User;
import com.example.MarketplaceTechforge4.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        try {
            List<User> allUsers = repository.findAll();
            return ResponseEntity.ok(allUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuários");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateUser(@RequestBody @Valid RequestUser data, @PathVariable String id) {
        try {
            Optional<User> optionalUser = repository.findById(Integer.parseInt(id));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setNome(data.getNome());
                user.setEmail(data.getEmail());
                repository.save(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário");
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid RequestUser data) {
        try {
            User newUser = new User(data);
            User savedUser = repository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id){
        Optional<User> optionalUser = repository.findById(Integer.parseInt(id));
        if (optionalUser.isPresent()) {
            repository.delete(optionalUser.get());

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            Optional<User> optionalUser = repository.findById(Integer.parseInt(id));
            if (optionalUser.isPresent()) {
                return ResponseEntity.ok(optionalUser.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
