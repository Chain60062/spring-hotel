package viniciusmmenezes.springhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import viniciusmmenezes.springhotel.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SecurityService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  repository.findByEmail(username);
        //UserDetailsService does not allow returning null
        if(user == null){
            throw new UsernameNotFoundException("Usuário não existe.");
        }
        return user;
    }
}
