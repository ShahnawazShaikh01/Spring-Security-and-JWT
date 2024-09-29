package com.shanu.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserService myUserService;
    @GetMapping("/home")
    public String greet(){
        return "hello world";
    }

    @GetMapping("/user/home")
    public String greetUser(){
        return "hello user";
    }

    @GetMapping("/admin/home")
    public String greetAdmin(){
        return "hello admin";
    }
    @PostMapping("/authenticate")
    public String generateAndGetToken(@RequestBody LoginForm loginForm){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(),loginForm.password()
        ));
        if(authentication.isAuthenticated()){
           return jwtService.generateToken(myUserService.loadUserByUsername(loginForm.username()));

        }else{
            throw new UsernameNotFoundException("invalid user");
        }
    }

}

