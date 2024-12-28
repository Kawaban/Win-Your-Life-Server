package com.example.winyourlife.authentication.domain;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
record PasswordResetController(PasswordResetService passwordResetService) {

    @PostMapping("/remind-password/{email}")
    public void remindPassword(@PathVariable String email) throws MessagingException {
        passwordResetService.createPasswordResetToken(email);
    }
}
