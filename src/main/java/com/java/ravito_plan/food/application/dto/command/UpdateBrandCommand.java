package com.java.ravito_plan.food.application.dto.command;

import lombok.Getter;

public class UpdateBrandCommand implements BrandCommand {

    @Getter
    private Long id;
    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
