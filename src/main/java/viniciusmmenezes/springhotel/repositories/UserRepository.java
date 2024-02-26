package viniciusmmenezes.springhotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import viniciusmmenezes.springhotel.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    public UserDetails findByEmail(String email);
}
