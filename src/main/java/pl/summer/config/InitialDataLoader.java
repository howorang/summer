package pl.summer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.summer.consts.Privilege;
import pl.summer.model.entity.RoleEntity;
import pl.summer.model.entity.UserEntity;
import pl.summer.model.entity.UserInfoEntity;
import pl.summer.model.repository.RoleRepository;
import pl.summer.model.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Created by Piotr Borczyk on 25.04.2017.
 */

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isAlreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (isAlreadySetup) {
            return;
        }

        RoleEntity userRole = RoleEntity.builder()
                .roleName("ROLE_USER")
                .privilege(Privilege.ADD_ENTRY)
                .privilege(Privilege.DELETE_OWN_POSTS)
                .build();

        RoleEntity adminRole = RoleEntity.builder()
                .roleName("ROLE_ADMIN")
                .privilege(Privilege.ADD_ENTRY)
                .privilege(Privilege.DELETE_OWN_POSTS)
                .privilege(Privilege.DELETE_ALL_POSTS)
                .privilege(Privilege.ADMIN_PANEL)
                .build();

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        UserEntity admin = UserEntity.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .entries(Collections.emptyList())
                .userInfo(UserInfoEntity.builder()
                        .description("Admin account")
                        .avatar(new byte[]{})
                        .build())
                .role(userRole)
                .role(adminRole)
                .build();

        UserEntity solBadguy = UserEntity.builder()
                .username("sol_badguy")
                .password(passwordEncoder.encode("gunflame"))
                .entries(Collections.emptyList())
                .userInfo(UserInfoEntity.builder()
                        .description("normal account")
                        .avatar(new byte[]{})
                        .build())
                .role(userRole)
                .build();

        userRepository.save(admin);
        userRepository.save(solBadguy);

        isAlreadySetup = true;
    }
}