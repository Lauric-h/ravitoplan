package com.java.ravito_plan.food.application.dto.internal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodDetail {

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
