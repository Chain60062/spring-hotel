package com.vinicius.springhotel.config;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.vinicius.springhotel.models.ApplicationUser;
import com.vinicius.springhotel.enums.Role;
import com.vinicius.springhotel.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        var u1 = new ApplicationUser(null,  "maria", "maria@gmail.com", Role.ADMIN.toString(), "1234");
        var u2 = new ApplicationUser(null, "Alex Green", "alex@gmail.com",  Role.USER.toString(), "977777777");
        
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
