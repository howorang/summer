package pl.summer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.summer.model.entity.RoleEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by howor on 18.04.2017.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;

        userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }

        return User
                .withUsername(username)
                .password(userEntity.getPassword())
                .authorities(getGrantedAuthorities(userEntity))
                .build();
    }

    private static  List<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
        return user.getRoles().stream()
                .map(RoleEntity::getPrivileges)
                .flatMap(Collection::stream)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
