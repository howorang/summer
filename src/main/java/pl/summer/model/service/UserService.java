package pl.summer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.summer.model.dto.UserRegistrationDto;
import pl.summer.model.entity.RoleEntity;
import pl.summer.model.entity.UserInfoEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.repository.RoleRepository;
import pl.summer.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Created by howor on 15.04.2017.
 */

@Service
@Transactional
public class UserService {

    private static final int DEFAULT_PAGE_SIZE = 1;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerNewUser(UserRegistrationDto userDto) {

        RoleEntity userRole = roleRepository.findByRoleName("ROLE_USER");

        UserEntity newUser = UserEntity.builder()
                .username(userDto.getUsername())
                .password((passwordEncoder.encode(userDto.getPassword())))
                .entries(Collections.emptyList())
                .role(userRole)
                .userInfo(
                        UserInfoEntity.builder()
                        .description("")
                        .avatar("")
                                .build())

                .build();
        userRepository.save(newUser);
    }

    public UserEntity getCurrentlyLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }

    public Page<UserEntity> getUsers(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE);
        return userRepository.findAll(pageRequest);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
