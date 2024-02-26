package viniciusmmenezes.springhotel.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements UserDetails {

    public User(@Size(min = 11, max = 11) String cpf, @Email @NotNull String email, @NotNull String firstName,
            @NotNull String lastName, @NotNull String password, @NotNull String authority,
            @NotNull LocalDate dateOfBirth) {
        this.cpf = cpf;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
        this.dateOfBirth = dateOfBirth;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Size(min = 11, max = 11)
    @Column(length = 11)
    private String cpf;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private String authority;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "customer")
    private Set<Phone> phones = new HashSet<>();
    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings = new HashSet<>();
    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses = new HashSet<>();

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}