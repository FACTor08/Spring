package com.factor.movies.Controller;

import com.factor.movies.Model.UsersDTO;
import com.factor.movies.Service.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factormovies")
public class UserRegistration{
    @Autowired
    private UserLogic logic;

    @PostMapping("/register")
    public ResponseEntity<String> addUsers(@RequestBody UsersDTO user){
        String msg = logic.registerUsers(user);
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/user-login")
    public ResponseEntity<String> loadUser(@RequestBody String username){
        logic.loadUserByUsername(username);
        return ResponseEntity.ok("login successful");
    }
    @DeleteMapping("/account/delete/{user}")
    public ResponseEntity<String> deleteAccount(@PathVariable String user){
        logic.deleteAccount(user);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
