package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteFoodCommand {

    @NotNull
    private Long foodId;

    @NotNull
    private Long raceId;

    @NotNull
    private Long checkpointId;

    @Min(1)
    private int quantity;
}
