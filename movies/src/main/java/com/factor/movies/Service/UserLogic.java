package com.factor.movies.Service;

import com.factor.movies.Configuration.UserDTransfer;
import com.factor.movies.Model.Users;
import com.factor.movies.Model.UsersDTO;
import com.factor.movies.Repository.Userdb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserLogic {
    @Autowired
    private Userdb repo;

    @Autowired
    private UserDTransfer userDT;

    public String registerUsers(UsersDTO data){

        if(repo.findByEmailIgnoreCase(data.getEmail()).isPresent()){
            return "Email already exists!!!";
        }

        Users store = userDT.transferobj(data);
       repo.save(store);
       return "User successfully registered";
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
    public Optional<Users> updateData(String password, UsersDTO key) {
        return repo.findByUsername(password).map(data -> {
            if (key.getUsername() != null) {
                data.setUsername(key.getUsername());
            }
            if (key.getPassword() != null) {
                data.setPassword(key.getPassword());
            }
            return repo.save(data);
        });
    }
    public void deleteAccount(String user){
        repo.deleteByUsernameIgnoreCase(user);
    }
}
