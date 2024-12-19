package com.java.ravito_plan.race.application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExternalFoodDto {

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
