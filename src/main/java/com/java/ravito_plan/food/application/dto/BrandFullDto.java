package com.java.ravito_plan.food.application.dto;

import java.util.List;

public class BrandFullDto {
    public String name;
    public List<FoodDto> foods;
    public BrandFullDto(String name, List<FoodDto> foods) {
        this.name = name;
        this.foods = foods;
    }
}