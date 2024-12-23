package com.java.ravito_plan.food.application.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.NotFoundException;

public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException(Long id) {
        super(String.format("Brand not found with id %d", id), ErrorCode.BRAND_NOT_FOUND);
    }
}
