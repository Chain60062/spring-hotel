package com.vinicius.springhotel.domain.dto;

import com.vinicius.springhotel.enums.Role;

// equivalente ao ViewModel do Asp.Net core
public record RegisterDTO(String firstName, String lastName, String password, Role role, String email) {

}
