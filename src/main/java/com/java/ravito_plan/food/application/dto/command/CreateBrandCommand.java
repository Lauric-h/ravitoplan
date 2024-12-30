package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.NotBlank.List;

public class CreateBrandCommand implements BrandCommand {

    @NotBlank
    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
