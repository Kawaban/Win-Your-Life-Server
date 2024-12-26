package com.example.winyourlife.authentication.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.example.winyourlife.authentication")
class AuthenticationProperties {
    private String activationLink;
    private String activationMessage;
    private String serverBase;
    private String passwordResetMsg;
    private String passwordResetLink;
}
