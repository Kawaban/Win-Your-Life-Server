package com.example.winyourlife.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
    private String status;
    private String message;
    private Integer statusCode;
    private String path;
}
