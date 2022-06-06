package com.assignment.controller;

import com.assignment.handler.LoginHandler;
import com.assignment.model.LoginModel;
import com.assignment.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private LoginHandler loginHandler;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginModel loginModel, HttpServletRequest httpServletRequest) {
//        new BCryptPasswordEncoder().encode("1234");
        return loginHandler.login(loginModel, httpServletRequest);
    }
}