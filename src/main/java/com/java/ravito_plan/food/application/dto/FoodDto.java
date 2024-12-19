package com.java.ravito_plan.food.application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodDto {

    public Long id;
    public String name;
    public int carbohydrates;
    public int calories;
    public int proteins;
    public boolean electrolytes;
    public String link;
    public String comment;
    public String type;
}
