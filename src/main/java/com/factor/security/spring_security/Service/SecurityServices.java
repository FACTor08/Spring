package com.factor.security.spring_security.Service;

import com.factor.security.spring_security.Model.Security;
import com.factor.security.spring_security.Repository.SecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServices implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private  SecurityRepo repos;
   private Security user;
    public SecurityServices(SecurityRepo repos, BCryptPasswordEncoder encoder) {
        this.repos = repos;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var users = repos.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("user" + username + "not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
    public void UserRegistration(Security security) {
        security.setPassword(encoder.encode(security.getPassword()));
        repos.save(security);
    }
}

