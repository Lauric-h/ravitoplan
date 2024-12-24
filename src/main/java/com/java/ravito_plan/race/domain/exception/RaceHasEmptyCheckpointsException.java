package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class RaceHasEmptyCheckpointsException extends DomainException {

    public RaceHasEmptyCheckpointsException() {
        super("Race must have at least two checkpoints: start and finish.",
                ErrorCode.RACE_EMPTY_CHECKPOINTS);
    }
}
