package com.java.ravito_plan.user.domain.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.DomainException;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String userIdentifier) {
        super(String.format("User %s already exists", userIdentifier), ErrorCode.USER_ALREADY_EXISTS);
    }
}
