package com.java.ravito_plan.food.application.dto.command;

public class CreateFoodCommand implements FoodCommand {

    private Long brandId;
    private String name;
    private int carbohydrates;
    private int calories;
    private int proteins;
    private boolean electrolytes;
    private String link;
    private String comment;
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
