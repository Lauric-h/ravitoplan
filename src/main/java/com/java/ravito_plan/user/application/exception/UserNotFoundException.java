package com.java.ravito_plan.user.application.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String identifier) {
        super(String.format("User %s not found", identifier), ErrorCode.USER_NOT_FOUND);
    }
}
