package com.example.winyourlife.task;

import com.example.winyourlife.task.dto.*;
import com.example.winyourlife.userinfo.domain.UserInfo;
import java.util.List;

public interface TaskService {
    void createTask(CreateTaskRequest createTaskRequest);

    List<TaskResponse> getTasks();

    void updateTask(TaskUpdate taskUpdate);

    void deleteTask(TaskDelete taskDelete);

    void completeTasks(List<TaskCompletion> taskCompletion);

    void prepareTasks(TaskPreparation taskPreparation);

    void scheduledEndOfDay(UserInfo userInfo);
}
