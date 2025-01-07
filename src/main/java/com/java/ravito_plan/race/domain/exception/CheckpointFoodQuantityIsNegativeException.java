package com.java.ravito_plan.race.domain.exception;

public class CheckpointFoodQuantityIsNegativeException extends RuntimeException {

    public CheckpointFoodQuantityIsNegativeException(Long foodId, int quantity) {
        super(String.format("CheckpointFood for food %d is %d", foodId, quantity));
    }
}
