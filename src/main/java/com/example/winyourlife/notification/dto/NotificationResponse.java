package com.example.winyourlife.notification.dto;

import java.util.UUID;

public record NotificationResponse(String type, String emailSender, UUID id) {}
