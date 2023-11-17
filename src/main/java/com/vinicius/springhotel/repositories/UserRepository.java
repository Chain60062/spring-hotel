package com.vinicius.springhotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.vinicius.springhotel.domain.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    public UserDetails findByUsername(String username);
}
