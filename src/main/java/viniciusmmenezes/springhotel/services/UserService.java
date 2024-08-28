
package viniciusmmenezes.springhotel.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import viniciusmmenezes.springhotel.models.User;
import viniciusmmenezes.springhotel.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository _repository) {
        repository = _repository;
    }

    public UserDetails findByEmail(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public User insert(User user) {
        return repository.save(user);
    }
}
