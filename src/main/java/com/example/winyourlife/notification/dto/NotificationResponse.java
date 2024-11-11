package com.example.winyourlife.notification.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record NotificationResponse(String type, String emailSender, UUID id, String nickSender, String time) {}
