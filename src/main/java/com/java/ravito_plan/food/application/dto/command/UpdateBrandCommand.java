package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandCommand implements BrandCommand {

    @NotBlank
    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
