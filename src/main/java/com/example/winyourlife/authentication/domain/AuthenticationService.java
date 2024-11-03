package com.example.winyourlife.authentication.domain;

import com.example.winyourlife.authentication.dto.LoginRequest;
import com.example.winyourlife.authentication.dto.LoginResponse;
import com.example.winyourlife.authentication.dto.RegisterRequest;
import com.example.winyourlife.authentication.user.UserService;
import com.example.winyourlife.authentication.user.dto.UserRequest;
import com.example.winyourlife.jwt.JwtService;
import com.example.winyourlife.jwt.dto.JwtUser;
import com.example.winyourlife.userinfo.UserInfoService;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationService(
        PasswordEncoder passwordEncoder,
        UserService userService,
        UserInfoService userInfoService,
        JwtService jwtService,
        AuthenticationManager authenticationManager
) implements com.example.winyourlife.authentication.AuthenticationService {


    @Override
    public void register(RegisterRequest registerRequest) {
        System.out.println(registerRequest.email());
        System.out.println(registerRequest.name());
        val userRequest = UserRequest.builder()
                .email(registerRequest.email())
                .password(passwordEncoder.encode(new String(registerRequest.password())).toCharArray())
                .build();
        userService.createUser(userRequest);

        System.out.println(registerRequest.email());
        System.out.println(registerRequest.name());

        val userInfoRequest = com.example.winyourlife.userinfo.dto.UserInfoRequest.builder()
                .email(registerRequest.email())
                .name(registerRequest.name())
                .build();
        userInfoService.createUserInfo(userInfoRequest);

        registerRequest.zeroPassword();
        userRequest.zeroPassword();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        val user = userService.loadUserByUsername(loginRequest.email());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(), new String(loginRequest.password()), user.getAuthorities()));
        loginRequest.zeroPassword();

        val token = jwtService().generateToken(new JwtUser(user.getUsername()));
        return new LoginResponse(token);
    }
}
