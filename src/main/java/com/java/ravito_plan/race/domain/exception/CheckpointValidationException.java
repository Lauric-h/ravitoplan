package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class CheckpointValidationException extends DomainException {

    public CheckpointValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
