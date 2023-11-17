package com.vinicius.springhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinicius.springhotel.domain.ApplicationUser;
import com.vinicius.springhotel.repositories.UserRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public ApplicationUser insert(ApplicationUser user) {
        return repository.save(user);
    }
}
