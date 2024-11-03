package com.example.winyourlife.userinfo.dto;

public record UserInfoResponse(
        String email,
        String name,
        String avatar,
        int streak,
        int longestStreak,
        int completedTasks,
        boolean isFriendNotificationActive,
        boolean isDailyReminderActive) {}
