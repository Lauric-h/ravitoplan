package com.java.ravito_plan.food.application.exception;

import com.java.ravito_plan.common.api.error.ErrorCode;
import com.java.ravito_plan.common.exception.NotFoundException;

public class FoodNotFoundException extends NotFoundException {

    public FoodNotFoundException(Long id) {
        super(String.format("Food not found with id %d", id), ErrorCode.FOOD_NOT_FOUND);
    }
}
