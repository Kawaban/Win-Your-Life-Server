package com.example.winyourlife.jwt.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.example.winyourlife.jwt")
public class JwtProperties {
    private String secretKey;
    private int expirationTimeMillis;
}
