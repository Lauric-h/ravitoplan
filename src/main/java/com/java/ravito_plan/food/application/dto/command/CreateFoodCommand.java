package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateFoodCommand implements FoodCommand {

    @NotNull
    private Long brandId;
    @NotBlank
    private String name;
    @NotNull
    private int carbohydrates;
    private int calories;
    private int proteins;
    private boolean electrolytes;
    private String link;
    private String comment;
    @NotBlank
    private String ingestionType;

    @Override
    public Long getBrandId() {
        return this.brandId;
    }

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
