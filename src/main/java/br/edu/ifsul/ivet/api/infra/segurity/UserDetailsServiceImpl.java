package br.edu.ifsul.ivet.api.infra.segurity;

import br.edu.ifsul.ivet.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRep;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        br.edu.ifsul.ivet.api.user.User user = userRep.findByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return User.withUsername(username).password(user.getSenha()).roles("USER").build();
    }

}
