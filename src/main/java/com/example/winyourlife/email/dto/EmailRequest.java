package com.example.winyourlife.email.dto;

public record EmailRequest(String recipientEmail, String subject, EmailTemplate emailTemplate) {}
