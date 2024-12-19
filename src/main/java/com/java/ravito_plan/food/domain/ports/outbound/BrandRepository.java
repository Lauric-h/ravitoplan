package com.java.ravito_plan.food.domain.ports.outbound;

import com.java.ravito_plan.food.domain.model.Brand;
import java.util.List;

public interface BrandRepository {

    Brand findById(Long id);

    Brand save(Brand brand);

    List<Brand> findAll();

    void deleteById(Long id);
}
