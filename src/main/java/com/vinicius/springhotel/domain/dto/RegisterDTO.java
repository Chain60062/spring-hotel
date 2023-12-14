package com.vinicius.springhotel.domain.dto;

import java.time.LocalDate;

import com.vinicius.springhotel.enums.Role;

public record RegisterDTO(String cpf, String firstName, String lastName, String password, Role role, String email,
        LocalDate dateOfBirth) {
}
