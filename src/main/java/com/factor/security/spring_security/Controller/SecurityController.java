package com.factor.security.spring_security.Controller;

import com.factor.security.spring_security.Service.SecurityServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityController {




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(SecurityServices services){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(services);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf(http -> http.disable())

                .authorizeHttpRequests(http -> http
                        .requestMatchers(("movieweb/upload")).hasRole("ROLE_ADMIN")
                        .requestMatchers(("movieweb/get")).permitAll()
                        .requestMatchers(("movieweb/get/{movie}")).permitAll()
                        .requestMatchers(("movieweb/get/{movie}/banner")).permitAll()
                        .requestMatchers(("movieweb/delete/{remove}")).hasRole("ROLE_ADMIN")
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }


}
