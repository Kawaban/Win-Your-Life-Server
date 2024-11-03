package com.example.winyourlife.authentication.user.domain;

import com.example.winyourlife.authentication.user.dto.UserRequest;
import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.infrastructure.exception.UserAlreadyExistsException;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
record UserService(UserRepository userRepository, UserMapper userMapper)
        implements com.example.winyourlife.authentication.user.UserService {
    @Override
    public void createUser(UserRequest userRequest) {
        if (existsByEmail(userRequest.email())) {
            throw new UserAlreadyExistsException();
        }
        val user = userMapper.userRequestToUser(userRequest);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws ApplicationEntityNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void changeEmail(String oldEmail, String newEmail) {
        val user = userRepository.findByEmail(oldEmail).orElseThrow(ApplicationEntityNotFoundException::new);
        user.setEmail(newEmail);
        userRepository.save(user);
    }
}
