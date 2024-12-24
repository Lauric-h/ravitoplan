package com.java.ravito_plan.race.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class CannotCreateSegmentWitCheckpointException extends DomainException  {

    public CannotCreateSegmentWitCheckpointException(String message) {
        super(message, ErrorCode.CANNOT_CREATE_SEGMENT_WITH_CHECKPOINT);
    }
}
