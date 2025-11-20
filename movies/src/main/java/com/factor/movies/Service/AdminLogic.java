package com.factor.movies.Service;

import com.factor.movies.Configuration.AdminDTransfer;
import com.factor.movies.Model.Admin;
import com.factor.movies.Model.AdminDTO;
import com.factor.movies.Repository.Admindb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@Transactional
public class AdminLogic {

    @Autowired
    private Admindb repo;
    @Autowired
    private AdminDTransfer trnsf;

    public String registerAdmin(AdminDTO data){

    if(repo.findByEmail(data.getEmail()).isPresent()){
        return "Email already exists";
    }

        Admin store = trnsf.transferobj(data);
            repo.save(store);
        return "New Admin Successfully Registered";
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
