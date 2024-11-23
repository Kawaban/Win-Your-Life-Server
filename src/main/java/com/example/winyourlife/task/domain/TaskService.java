package com.example.winyourlife.task.domain;

import com.example.winyourlife.task.dto.CreateTaskRequest;
import com.example.winyourlife.task.dto.TaskResponse;
import com.example.winyourlife.userinfo.domain.UserInfoService;
import java.util.List;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public record TaskService(TaskRepository taskRepository, UserInfoService userInfoService, TaskMapper taskMapper)
        implements com.example.winyourlife.task.TaskService {

    @Override
    public void createTask(CreateTaskRequest createTaskRequest) {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        val userInfo = userInfoService.getUserInfoByEmail(user.getUsername());

        val task = taskMapper.toTask(createTaskRequest);
        task.setUser(userInfo);
        taskRepository.save(task);
    }

    @Override
    public List<TaskResponse> getTasks() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        val userInfo = userInfoService.getUserInfoByEmail(user.getUsername());
        return taskRepository.findAllByUser(userInfo).stream()
                .map(taskMapper::toTaskResponse)
                .toList();
    }
}
