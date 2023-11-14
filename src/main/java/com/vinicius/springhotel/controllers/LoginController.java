package com.vinicius.springhotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springhotel.domain.ApplicationUser;
import com.vinicius.springhotel.domain.dto.LoginDTO;
import com.vinicius.springhotel.domain.dto.RegisterDTO;
import com.vinicius.springhotel.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginDTO loginRequest) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.username(),
                loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> login(@RequestBody @Valid RegisterDTO registerRequest) {
        if (repository.findByUsername(registerRequest.username()) != null)
            return ResponseEntity.badRequest().build();

            var argon2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
            var cipher = argon2.encode(registerRequest.password());
            var user = new ApplicationUser(null, registerRequest.username(), registerRequest.email(), registerRequest.role().name(), cipher);

            repository.save(user);
            return ResponseEntity.ok().body(user);
    }

    @GetMapping("/open")
    public String open() {
        return "open route";
    }
}