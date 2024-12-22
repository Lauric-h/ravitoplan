package com.java.ravito_plan.food.application.dto.view;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrandDetailView {
    public String name;
    public List<FoodSummaryView> foods;
}
