package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.rest.error.ErrorCode;

public class InvalidCheckpointElevationException extends CheckpointValidationException {

    public InvalidCheckpointElevationException(String message) {
        super(message, ErrorCode.INVALID_ELEVATION);
    }
}
