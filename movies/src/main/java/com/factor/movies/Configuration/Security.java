package com.factor.movies.Configuration;

import com.factor.movies.Model.Roles;
import com.factor.movies.Service.AdminLogic;
import com.factor.movies.Service.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
@Configuration


public class Security {
    @Bean
    public UserDetailsService userDetailsService(AdminLogic adminLogic, UserLogic userLogic) {
        return username -> {
            try {
                return adminLogic.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                return userLogic.loadUserByUsername(username);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/factormovies/register").permitAll()
                        .requestMatchers("/factormovies/register-Administrators").permitAll()
                        .requestMatchers("/factormovies/upload").hasRole("ADMIN")
                        .requestMatchers("/factormovies/patch/{detail}").hasRole("ADMIN")
                        .requestMatchers("/factormovies/delete/{movies}").hasRole("ADMIN")
                        .requestMatchers("/factormovies/user-login").hasRole("USER")
                        .requestMatchers("/factormovies/admin-login}").hasRole("ADMIN")
                        .requestMatchers("/factormovies/delete/{username}").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
