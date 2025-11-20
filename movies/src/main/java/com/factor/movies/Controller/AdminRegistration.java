package com.factor.movies.Controller;

import com.factor.movies.Model.AdminDTO;
import com.factor.movies.Service.AdminLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/factormovies")
public class AdminRegistration {

    @Autowired
    private AdminLogic logic;

    @PostMapping("/register-Administrators")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO details){
       String msg = logic.registerAdmin(details);
         return ResponseEntity.ok(msg);
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> loadUser(@RequestBody String username){
//        logic.loadUserByUsername(username);
//        return ResponseEntity.ok("login successful");
//    }
}
