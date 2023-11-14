package com.vinicius.springhotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import com.vinicius.springhotel.models.ApplicationUser;
import com.vinicius.springhotel.services.UserService;

import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/users")
@NoArgsConstructor
@Data
public class UserController {
    private UserService service;

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getUsers() {
        List<ApplicationUser> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUser(@PathVariable Long id) {
        ApplicationUser user = service.findOne(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ApplicationUser user) {
        ApplicationUser newUser = service.insert(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}