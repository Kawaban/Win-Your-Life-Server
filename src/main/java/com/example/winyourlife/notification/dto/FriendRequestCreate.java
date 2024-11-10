package com.example.winyourlife.notification.dto;

import jakarta.validation.constraints.NotNull;

public record FriendRequestCreate(@NotNull String emailRecipient) {}
