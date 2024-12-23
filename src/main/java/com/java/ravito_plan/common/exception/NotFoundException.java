package com.java.ravito_plan.common.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
