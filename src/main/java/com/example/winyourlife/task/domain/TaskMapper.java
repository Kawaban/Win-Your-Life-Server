package com.example.winyourlife.task.domain;

import com.example.winyourlife.task.dto.CreateTaskRequest;
import com.example.winyourlife.task.dto.TaskResponse;
import java.util.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "taskImage", source = "taskImage", qualifiedByName = "encodeImage")
    TaskResponse toTaskResponse(Task task);

    @Mapping(target = "taskImage", source = "taskImage", qualifiedByName = "decodeImage")
    Task toTask(CreateTaskRequest createTaskRequest);

    @Named("encodeImage")
    default String encodeImage(byte[] taskImage) {
        return taskImage != null ? Base64.getEncoder().encodeToString(taskImage) : null;
    }

    @Named("decodeImage")
    default byte[] decodeImage(String taskImage) {
        return taskImage != null ? Base64.getDecoder().decode(taskImage) : null;
    }
}
