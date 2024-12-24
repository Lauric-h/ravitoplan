package com.java.ravito_plan.common.api.error;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiError {

    private final String message;
    private final String code;
    private final LocalDateTime timestamp;

    public ApiError(String message, ErrorCode code) {
        this.message = message;
        this.code = code.name();
        this.timestamp = LocalDateTime.now();
    }
}
