package com.example.winyourlife.authentication.domain;

import com.example.winyourlife.authentication.AuthenticationService;
import com.example.winyourlife.authentication.dto.LoginRequest;
import com.example.winyourlife.authentication.dto.LoginResponse;
import com.example.winyourlife.authentication.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
record AuthenticationController(AuthenticationService authenticationService) {

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
