package com.example.winyourlife.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class ApiErrorWrapper {
    @JsonProperty("Error")
    private ApiError errorBody;
}
