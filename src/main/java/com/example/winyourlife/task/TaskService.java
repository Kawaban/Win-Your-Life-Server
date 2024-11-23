package com.example.winyourlife.task;

import com.example.winyourlife.task.dto.CreateTaskRequest;
import com.example.winyourlife.task.dto.TaskResponse;
import java.util.List;

public interface TaskService {
    void createTask(CreateTaskRequest createTaskRequest);

    List<TaskResponse> getTasks();
}
