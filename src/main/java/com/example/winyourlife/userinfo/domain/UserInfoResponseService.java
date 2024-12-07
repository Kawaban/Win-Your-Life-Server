package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.task.TaskService;
import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public record UserInfoResponseService(UserInfoService userInfoService, TaskService taskService) {
    public UserInfoResponse getUserInfoWithTasks() {
        val userInfoDto = userInfoService.getUserInfoWithOutTasks();
        userInfoDto.activeTasks().addAll(taskService.getActiveTasks());
        userInfoDto.preparedTasks().addAll(taskService.getPreparedTasks());
        userInfoDto.allTasks().addAll(taskService.getTasks());
        return userInfoDto;
    }
}
