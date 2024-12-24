package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;

public class InvalidCheckpointOrderException extends CheckpointValidationException {

    public InvalidCheckpointOrderException(Long currentCheckpointId, Long previousCheckpointId) {
        super(String.format("Checkpoint %s cannot be before checkpoint %s", currentCheckpointId,
                previousCheckpointId), ErrorCode.INVALID_CHECKPOINT_ORDER);
    }
}
