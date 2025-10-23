package com.factor.security.spring_security.Controller;

import com.factor.security.spring_security.Service.SecurityServices;
import com.factor.security.spring_security.Model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserRegistryController {
    @Autowired
    SecurityServices securityServices;

        public UserRegistryController(SecurityServices securityServices) {
            this.securityServices = securityServices;
        }

        @PostMapping("/register")
        public String registerUser(@RequestBody Security security) throws IOException {
            securityServices.UserRegistration(security);
            return "User registered successfully";
        }

}
