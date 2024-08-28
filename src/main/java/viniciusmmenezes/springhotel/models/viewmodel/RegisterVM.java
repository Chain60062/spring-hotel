package viniciusmmenezes.springhotel.models.viewmodel;

import java.time.LocalDate;

import viniciusmmenezes.springhotel.enums.Role;

public record RegisterVM(String cpf, String firstName, String lastName, String password, Role role, String email,
        LocalDate dateOfBirth) {
}
