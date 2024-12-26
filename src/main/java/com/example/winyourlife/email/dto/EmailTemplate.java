package com.example.winyourlife.email.dto;

import java.util.Map;

public record EmailTemplate(String templateName, Map<String, Object> templateModel) {}
