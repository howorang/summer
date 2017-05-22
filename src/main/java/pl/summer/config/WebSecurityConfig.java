package pl.summer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.summer.consts.Privilege;

import javax.sql.DataSource;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/webjars/bootstrap/**").permitAll()
                .antMatchers("/webjars/jquery/**").permitAll()
                .antMatchers("/css/**", "/login").permitAll()
                .antMatchers("/css/**", "/resetPassword").permitAll()
                .antMatchers("/entry/add").hasAuthority(Privilege.ADD_ENTRY.name())
                .antMatchers("/entry/remove").hasAuthority(Privilege.DELETE_ALL_POSTS.name())
                .antMatchers("/admin").hasAuthority(Privilege.ADMIN_PANEL.name())
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}