//package com.factor.security.spring_security.Service;
//
//import com.factor.security.spring_security.Model.Security;
//import com.factor.security.spring_security.Repository.SecurityRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecurityServices implements UserDetailsService {
//
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//    private  SecurityRepo repos;
//   private Security security;
//    public SecurityServices(SecurityRepo repos) {
//        this.repos = repos;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var user = repos.findByUserName(username)
//                .orElseThrow(()-> new UsernameNotFoundException("user" + username + "not found"));
//        return org.springframework.security.core.userdetails.User
//                .withUsername(security.getUserName())
//                .password(security.getPassword())
//                .roles(security.getRole())
//                .build();
//    }
//    public void UserRegistration(Security security) {
//        security.setPassword(encoder.encode(security.getPassword()));
//        repos.save(security);
//    }
//}
//
