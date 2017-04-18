package pl.summer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.repository.UserRepository;

import java.util.Collections;

/**
 * Created by howor on 18.04.2017.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;

        userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }

        return User
                .withUsername(username)
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
