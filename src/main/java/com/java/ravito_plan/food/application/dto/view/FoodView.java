package com.java.ravito_plan.food.application.dto.view;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodView {

    public String brandName;
    public String name;
    public int carbohydrates;
    public int calories;
    public int proteins;
    public boolean electrolytes;
    public String link;
    public String comment;
    public String ingestionType;
}
