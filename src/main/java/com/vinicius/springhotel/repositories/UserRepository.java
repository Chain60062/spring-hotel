package com.vinicius.springhotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.vinicius.springhotel.domain.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    // @Query("SELECT id, username, email, password, authority FROM ApplicationUser u WHERE u.username = ?1")
    public UserDetails findByUsername(String username);
}
