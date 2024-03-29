package com.challengescrd.challenge.controller;

import com.challengescrd.challenge.entities.User;
import com.challengescrd.challenge.entities.UserRepository;
import com.challengescrd.challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de usuário")
    @Transactional
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    @Operation(summary = "Ficha de usuários")
    @Transactional
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encontre usuário por ID")
    @Transactional
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Atualização de usuário")
    @Transactional
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminação de usuário por ID")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
