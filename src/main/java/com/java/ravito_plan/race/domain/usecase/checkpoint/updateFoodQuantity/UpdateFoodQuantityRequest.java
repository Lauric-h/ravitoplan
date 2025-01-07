package com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity;

public record UpdateFoodQuantityRequest(Long raceId, Long userId, Long checkpointId, Long foodId,
                                        int quantity) {

}
