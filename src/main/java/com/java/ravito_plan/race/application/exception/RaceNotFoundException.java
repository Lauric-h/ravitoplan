package com.java.ravito_plan.race.application.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.NotFoundException;

public class RaceNotFoundException extends NotFoundException {

    public RaceNotFoundException(Long id) {
        super(String.format("Race %s not found", id), ErrorCode.RACE_NOT_FOUND);
    }
}
