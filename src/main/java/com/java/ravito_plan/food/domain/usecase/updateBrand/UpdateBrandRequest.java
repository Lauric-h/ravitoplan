package com.java.ravito_plan.food.domain.usecase.updateBrand;

import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;

public record UpdateBrandRequest(Long id, UpdateBrandCommand command) {

}
