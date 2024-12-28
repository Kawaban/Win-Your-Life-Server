package com.example.winyourlife.task.domain;

import com.example.winyourlife.task.dto.*;
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

    @GetMapping("/preparation")
    List<TaskResponse> getPreparedTasks() {
        return taskService.getPreparedTasks();
    }

    @GetMapping("/active")
    List<TaskResponse> getActiveTasks() {
        return taskService.getActiveTasks();
    }

    @PutMapping
    void updateTask(@RequestBody TaskUpdate taskUpdate) {
        taskService.updateTask(taskUpdate);
    }

    @DeleteMapping("/{taskName}")
    void deleteTask(@PathVariable String taskName) {
        taskService.deleteTask(taskName);
    }

    @PatchMapping("/completion")
    void completeTasks(@RequestBody List<TaskCompletion> taskCompletion) {
        taskService.completeTasks(taskCompletion);
    }

    @PatchMapping("/preparation")
    void prepareTasks(@RequestBody TaskPreparation taskPreparation) {
        taskService.prepareTasks(taskPreparation);
    }
}
