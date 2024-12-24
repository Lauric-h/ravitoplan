package com.java.ravito_plan.race.application.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.NotFoundException;

public class CheckpointNotFoundException extends NotFoundException {

    public CheckpointNotFoundException(Long id) {
        super(String.format("Checkpoint %s not found", id), ErrorCode.CHECKPOINT_NOT_FOUND);
    }
}
