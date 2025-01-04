package com.java.ravito_plan.user.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String username, String email) {
        super(String.format("User %s %s already exists", username, email), ErrorCode.USER_ALREADY_EXISTS);
    }
}
