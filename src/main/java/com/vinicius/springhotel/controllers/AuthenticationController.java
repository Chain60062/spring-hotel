package com.vinicius.springhotel.controllers;

import java.net.URI;
import java.nio.file.attribute.UserPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springhotel.domain.ApplicationUser;
import com.vinicius.springhotel.domain.dto.LoginDTO;
import com.vinicius.springhotel.domain.dto.RegisterDTO;
import com.vinicius.springhotel.domain.dto.UserDTO;
import com.vinicius.springhotel.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService service;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginRequest, HttpServletRequest request,
            HttpServletResponse response) {
        //create unauthenticated token 
        Authentication token = new UsernamePasswordAuthenticationToken(loginRequest.username(),
                loginRequest.password());
        //authenticate it
        Authentication authentication = this.authenticationManager.authenticate(token);
        //set new context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        //save it in the session
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        //retrieve user information from the session
        ApplicationUser userPrincipal = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO user = new UserDTO(userPrincipal.getUsername(), userPrincipal.getEmail(),
                userPrincipal.getAuthority());

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> login(@RequestBody @Valid RegisterDTO registerRequest) {
        if (service.findByUsername(registerRequest.username()) != null)
            return ResponseEntity.badRequest().build();

        var argon2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String cipher = argon2.encode(registerRequest.password());
        var user = new ApplicationUser(null, registerRequest.username(), registerRequest.email(),
                registerRequest.role().name(), cipher);

        user = service.insert(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/open")
    public String open() {
        return "open route";
    }
}