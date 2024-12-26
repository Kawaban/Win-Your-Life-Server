package com.example.winyourlife.authentication.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Arrays;
import lombok.Builder;

@Builder
public record PasswordResetRequest(@NotNull String token, char @NotNull [] password) {
    public void zeroPassword() {
        Arrays.fill(password, (char) 0);
    }
}
