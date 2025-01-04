package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateFoodCommand {

    @NotBlank
    private String name;

    @NotNull
    private int carbohydrates;

    private int calories;

    private int proteins;

    @Builder.Default
    private boolean electrolytes = false;

    private String link;

    private String comment;

    @NotBlank
    private String ingestionType;
}
