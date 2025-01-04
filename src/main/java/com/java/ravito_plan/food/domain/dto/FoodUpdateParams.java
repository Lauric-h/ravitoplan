package com.java.ravito_plan.food.domain.dto;

public record FoodUpdateParams(String name, int carbohydrates, int calories, int proteins,
                               boolean electrolytes, String link, String comment,
                               String ingestionType) {

}
