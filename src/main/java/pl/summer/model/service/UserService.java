package pl.summer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.summer.model.dto.UserRegistrationDto;
import pl.summer.model.entity.UserDetailsEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Created by howor on 15.04.2017.
 */

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerNewUser(UserRegistrationDto userDto) {
        UserDetailsEntity userDetails = new UserDetailsEntity();
        UserEntity newUser = UserEntity.builder()
                .username(userDto.getUsername())
                .password((passwordEncoder.encode(userDto.getPassword())))
                .entries(Collections.emptyList())
                .userDetails(userDetails)
                .build();

        userRepository.save(newUser);
    }

    @Transactional
    public UserEntity getCurrentlyLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }
}
