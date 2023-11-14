package com.vinicius.springhotel.services;

import org.springframework.stereotype.Service;
import com.vinicius.springhotel.models.ApplicationUser;
import com.vinicius.springhotel.repositories.UserRepository;
import com.vinicius.springhotel.services.exceptions.ResourceNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public List<ApplicationUser> findAll() {
        return repository.findAll();
    }

    public ApplicationUser findOne(Long id) {
        var user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public ApplicationUser insert(ApplicationUser user) {
        user = repository.save(user);
        return user;
    }

    public ApplicationUser updateUsername(Long id, String username) {
        var user = repository.getReferenceById(id);

        user.setUsername(username);

        return repository.save(user);
    }

    public ApplicationUser updateEmail(Long id, String email) {
        var user = repository.getReferenceById(id);

        user.setEmail(email);

        return repository.save(user);
    }
}
