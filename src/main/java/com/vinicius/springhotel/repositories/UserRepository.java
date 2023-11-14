package com.vinicius.springhotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinicius.springhotel.models.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    @Query("SELECT id, username, email, password, authority FROM ApplicationUser u WHERE u.username = ?1")
    public ApplicationUser findUserByUserName(String username);
}
