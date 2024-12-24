package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;

public class CheckpointDistanceOverRaceDistanceException extends CheckpointValidationException {

    public CheckpointDistanceOverRaceDistanceException(int start, int distance) {
        super(String.format("Checkpoint start %s must be lower than race distance %s", start,
                distance), ErrorCode.CHECKPOINT_START_TOO_HIGH);
    }
}
