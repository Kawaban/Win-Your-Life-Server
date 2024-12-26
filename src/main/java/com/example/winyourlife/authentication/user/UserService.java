package com.example.winyourlife.authentication.user;

import com.example.winyourlife.authentication.user.dto.UserRequest;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserRequest userRequest);

    boolean existsByEmail(String email);

    void changeEmail(String oldEmail, String newEmail);

    void updatePassword(UUID userId, String password);

    UUID findUserIdByEmail(String email);
}
