package com.example.winyourlife.userinfo.dto;

import com.example.winyourlife.task.dto.TaskResponse;
import java.util.List;

public record UserInfoResponse(
        String email,
        String name,
        String avatar,
        int streak,
        int longestStreak,
        int completedTasks,
        int wonDays,
        int daysIn,
        List<TaskResponse> allTasks,
        List<TaskResponse> preparedTasks,
        List<TaskResponse> activeTasks) {}
