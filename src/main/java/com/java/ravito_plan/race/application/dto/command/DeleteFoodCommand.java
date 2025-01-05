package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class DeleteFoodCommand {

    @Builder.Default
    @Min(1)
    private int quantity = 1;

    public DeleteFoodCommand() {}
}
