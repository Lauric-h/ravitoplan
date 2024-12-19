package com.java.ravito_plan.food.application.dto.command;

public interface FoodCommand {

    Long getBrandId();
    String getName();
    int getCarbohydrates();
    int getCalories();
    int getProteins();
    boolean hasElectrolytes();
    String getLink();
    String getComment();
    String getIngestionType();
}
