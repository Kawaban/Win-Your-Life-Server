package com.example.winyourlife.authentication;

import com.example.winyourlife.authentication.dto.LoginRequest;
import com.example.winyourlife.authentication.dto.LoginResponse;
import com.example.winyourlife.authentication.dto.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
