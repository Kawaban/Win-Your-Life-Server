package com.example.winyourlife.authentication.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Arrays;
import lombok.Builder;

@Builder
public record UserRequest(@NotNull char[] password, @NotEmpty String email) {
    public void zeroPassword() {
        Arrays.fill(password, (char) 0);
    }
}
