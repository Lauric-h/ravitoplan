package com.java.ravito_plan.food.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UpdateBrandCommand implements BrandCommand {

    @NotNull
    @Getter
    private Long id;

    @NotBlank
    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
