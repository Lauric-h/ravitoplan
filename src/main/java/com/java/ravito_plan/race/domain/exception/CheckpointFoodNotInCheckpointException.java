package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class CheckpointFoodNotInCheckpointException extends DomainException {

    public CheckpointFoodNotInCheckpointException(Long foodId, Long checkpointId) {
        super(String.format("Food %s not found on checkpoint %s", foodId, checkpointId),
                ErrorCode.CHECKPOINT_FOOD_NOT_IN_CHECKPOINT);
    }
}
