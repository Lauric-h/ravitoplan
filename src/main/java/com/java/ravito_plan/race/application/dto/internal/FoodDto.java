package com.java.ravito_plan.race.application.dto.internal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodDto {

    public Long id;
    public String brandName;
    public String name;
    public int carbohydrates;
    public int calories;
    public int proteins;
    public boolean electrolytes;
    public String link;
    public String comment;
    public String type;
}
