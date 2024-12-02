package com.example.winyourlife.infrastructure.scheduler;

import com.example.winyourlife.task.TaskService;
import com.example.winyourlife.userinfo.UserInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public record EndOfDayScheduler(TaskService taskService, UserInfoService userInfoService) {

    @Scheduled(cron = "0 0 0 * * *")
    public void endOfDay() {
        userInfoService.getAllUsers().forEach(taskService::scheduledEndOfDay);
    }
}
