package com.example.winyourlife.email;

import com.example.winyourlife.email.dto.EmailRequest;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void sendMail(EmailRequest emailRequest) throws MessagingException;
}
