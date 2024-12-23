package com.java.ravito_plan.common.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final ErrorCode errorCode;

    public DomainException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
