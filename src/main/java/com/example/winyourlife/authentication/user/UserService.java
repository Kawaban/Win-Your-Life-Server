package com.example.winyourlife.authentication.user;

import com.example.winyourlife.authentication.user.dto.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserRequest userRequest);

    boolean existsByEmail(String email);

    void changeEmail(String oldEmail, String newEmail);
}
