

package viniciusmmenezes.springhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import viniciusmmenezes.springhotel.domain.User;
import viniciusmmenezes.springhotel.repositories.UserRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDetails findByEmail(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public User insert(User user) {
        return repository.save(user);
    }
}
