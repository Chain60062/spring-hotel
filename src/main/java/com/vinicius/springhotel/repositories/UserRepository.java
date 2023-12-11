package com.vinicius.springhotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.vinicius.springhotel.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public UserDetails findByEmail(String email);
}
