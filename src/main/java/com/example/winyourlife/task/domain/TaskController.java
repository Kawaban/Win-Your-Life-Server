package com.example.winyourlife.task.domain;

import com.example.winyourlife.task.dto.CreateTaskRequest;
import com.example.winyourlife.task.dto.TaskResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public record TaskController(TaskService taskService) {

    @PostMapping
    void createTask(CreateTaskRequest createTaskRequest) {
        taskService.createTask(createTaskRequest);
    }

    @GetMapping
    List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }
}
