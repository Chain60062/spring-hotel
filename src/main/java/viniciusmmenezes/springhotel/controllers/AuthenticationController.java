package viniciusmmenezes.springhotel.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import viniciusmmenezes.springhotel.models.viewmodel.*;
import viniciusmmenezes.springhotel.models.User;
import viniciusmmenezes.springhotel.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public final class AuthenticationController {

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
    public ResponseEntity<UserVM> login(@RequestBody @Valid LoginVM login, HttpServletRequest req,
            HttpServletResponse res) {
        // create authentication token
        var auth = createAndAuthenticateToken(login);
        // set new context and save it to the session
        saveAuthenticationToSession(auth, req, res);
        // retrieve user information from the session
        UserVM user = retrieveUserSessionInfo();

        return auth.isAuthenticated() ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> login(@RequestBody @Valid RegisterVM register) {
        // check if email is already in use
        if (service.findByEmail(register.email()) != null)
            return ResponseEntity.badRequest().build();
        // encrypt request password and save user to database
        String cipher = createCipher(register);
        User user = createAndSaveUser(register, cipher);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(user.getCpf())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/open")
    public String open() {
        return "open route";
    }

    private Authentication createAndAuthenticateToken(LoginVM login) {
        Authentication token = new UsernamePasswordAuthenticationToken(login.email(),
                login.password());
        // authenticate it
        return this.authenticationManager.authenticate(token);
    }

    private void saveAuthenticationToSession(Authentication authentication, HttpServletRequest req,
            HttpServletResponse res) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContext ctx = securityContextHolderStrategy.createEmptyContext();
        // save it in the session
        ctx.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(ctx);
        securityContextRepository.saveContext(ctx, req, res);
    }

    private UserVM retrieveUserSessionInfo() {
        User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return new UserVM(userPrincipal.getEmail(), userPrincipal.getAuthority());
    }

    private String createCipher(RegisterVM register) {
        var argon2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        return argon2.encode(register.password());

    }

    private User createAndSaveUser(RegisterVM register, String cipher) {
        var user = new User(register.cpf(), register.email(), register.firstName(),
                register.lastName(), cipher, register.role().name(), register.dateOfBirth());

        return service.insert(user);
    }
}