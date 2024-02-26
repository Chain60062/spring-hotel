package viniciusmmenezes.springhotel.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Employee extends User {

    public Employee(@Size(min = 11, max = 11) String cpf, @Email @NotNull String email, @NotNull String firstName,
            @NotNull String lastName, @NotNull String password, @NotNull String authority,
            @NotNull LocalDate dateOfBirth, BigDecimal salary, LocalDate hireDate) {
        super(cpf, email, firstName, lastName, password, authority, dateOfBirth);
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Employee(BigDecimal salary, LocalDate hireDate) {
        this.salary = salary;
        this.hireDate = hireDate;
    }

    private BigDecimal salary;
    private LocalDate hireDate;
    HotelUnit hotelUnit;
}
