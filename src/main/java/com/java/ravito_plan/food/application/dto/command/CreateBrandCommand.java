package com.java.ravito_plan.food.application.dto.command;

public class CreateBrandCommand implements BrandCommand {

    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
