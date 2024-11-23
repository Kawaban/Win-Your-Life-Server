package com.example.winyourlife.task.domain;

import com.example.winyourlife.task.dto.CreateTaskRequest;
import com.example.winyourlife.task.dto.TaskResponse;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public record TaskController(TaskService taskService) {

    @PostMapping
    void createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        taskService.createTask(createTaskRequest);
    }

    @GetMapping
    List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }
}
