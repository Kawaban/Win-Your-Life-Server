package com.example.winyourlife.infrastructure.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public record InstantToStringFormatter() {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String format(java.time.Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
