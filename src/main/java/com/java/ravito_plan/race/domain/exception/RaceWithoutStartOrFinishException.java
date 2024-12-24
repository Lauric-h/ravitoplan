package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class RaceWithoutStartOrFinishException extends DomainException {

    public RaceWithoutStartOrFinishException() {
        super("Race must have a starting and finish checkpoint", ErrorCode.RACE_WITHOUT_START_OR_FINISH);
    }
}
