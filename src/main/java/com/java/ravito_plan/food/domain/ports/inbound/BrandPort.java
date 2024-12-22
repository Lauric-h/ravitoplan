package com.java.ravito_plan.food.domain.ports.inbound;

import com.java.ravito_plan.food.application.dto.command.CreateBrandCommand;
import com.java.ravito_plan.food.application.dto.command.UpdateBrandCommand;
import com.java.ravito_plan.food.application.dto.view.BrandDetailView;
import com.java.ravito_plan.food.application.dto.view.BrandSummaryView;
import java.util.List;

public interface BrandPort {

    List<BrandSummaryView> getAllBrands();

    BrandDetailView getBrandById(Long id);

    BrandDetailView createBrand(CreateBrandCommand command);

    void deleteBrand(Long id);

    BrandDetailView updateBrand(UpdateBrandCommand command);
}
