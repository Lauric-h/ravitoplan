package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class CreateFoodCommand implements FoodCommand {

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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCarbohydrates() {
        return this.carbohydrates;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }

    @Override
    public int getProteins() {
        return this.proteins;
    }

    @Override
    public boolean hasElectrolytes() {
        return this.electrolytes;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public String getIngestionType() {
        return this.ingestionType;
    }
}
