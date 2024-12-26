package com.example.winyourlife.email.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.example.winyourlife.email")
class EmailProperties {
    private String username;
    private String password;
    private String host;
    private Integer port;
}
