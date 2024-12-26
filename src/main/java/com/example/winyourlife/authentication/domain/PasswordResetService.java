package com.example.winyourlife.authentication.domain;

import com.example.winyourlife.authentication.dto.PasswordResetRequest;
import com.example.winyourlife.authentication.user.UserService;
import com.example.winyourlife.email.EmailService;
import com.example.winyourlife.email.dto.EmailRequest;
import com.example.winyourlife.email.dto.EmailTemplate;
import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.infrastructure.exception.PasswordResetTokenExpiredException;
import com.example.winyourlife.userinfo.UserInfoService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.UUID;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
record PasswordResetService(
        EmailService emailService,
        PasswordResetTokenRepository passwordResetTokenRepository,
        UserService userService,
        AuthenticationProperties authenticationProperties,
        PasswordEncoder passwordEncoder,
        UserInfoService userInfoService) {

    public void createPasswordResetToken(String userEmail)
            throws ApplicationEntityNotFoundException, MessagingException {
        val userid = userService.findUserIdByEmail(userEmail);

        val userInfo = userInfoService.getUserInfoByEmail(userEmail);

        val passwordResetToken = PasswordResetToken.builder()
                .token(UUID.randomUUID().toString())
                .userUUID(userid)
                .build();
        passwordResetTokenRepository.save(passwordResetToken);

        final String link = authenticationProperties.getServerBase()
                + authenticationProperties.getPasswordResetLink()
                + passwordResetToken.getToken();

        val emailTemplate = new EmailTemplate(
                "password-reset", Map.of("passwordRecoveryLink", link, "firstName", userInfo.getName()));
        val emailRequest = new EmailRequest(userEmail, "Password Reset", emailTemplate);

        emailService.sendMail(emailRequest);
    }

    public void resetPassword(PasswordResetRequest request)
            throws ApplicationEntityNotFoundException, PasswordResetTokenExpiredException {
        val passwordResetToken = validateToken(request.token());
        userService.updatePassword(
                passwordResetToken.getUserUuid(), passwordEncoder.encode(new String(request.password())));
        passwordResetToken.setUsed(true);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    private PasswordResetToken validateToken(String token)
            throws ApplicationEntityNotFoundException, PasswordResetTokenExpiredException {

        val passwordResetToken =
                passwordResetTokenRepository.findByToken(token).orElseThrow(EntityNotFoundException::new);

        if (passwordResetToken.isExpired() || passwordResetToken.isUsed()) {
            throw new PasswordResetTokenExpiredException();
        }
        return passwordResetToken;
    }
}
