/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.logistics.controllers;

import com.logistics.auth.AuthenticationResponse;
import com.logistics.entities.Role;
import com.logistics.entities.User;
import com.logistics.repository.UserRepository;
import com.logistics.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.HashMap;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author estar
 */
@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;
    @Autowired
    UserRepository dataRepository;



    @GetMapping()
    public List<User> findAll() {
        return dataRepository.findAll();
    }

    @GetMapping("/get-all-by-role/{role}")
    public ResponseEntity<?> findAllByRol(@PathVariable Role role) {

        Optional<List<User>> data = dataRepository.findAllByRole(role);
        if(data.isPresent()) {
            return ResponseEntity.ok(data.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        User save = dataRepository.findById(id).get();
        return save;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody User input) {
        User save = dataRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody User input, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        if (input.getEmail() != null  && dataRepository.findByEmail(input.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "El Correo electrónico ya esta registrado."));
        }

        if (dataRepository.findByDocumentTypeAndDocumentNumber(input.getDocumentType(), input.getDocumentNumber()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "El Documento ya está registrado."));
        }
        AuthenticationResponse token =service.register(input);
        if(input.getRole() == Role.CLIENT){
           Optional<User> client = dataRepository.findByEmail(input.getEmail());
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<User> findById = dataRepository.findById(id);
        if (findById.get() != null) {
            dataRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
