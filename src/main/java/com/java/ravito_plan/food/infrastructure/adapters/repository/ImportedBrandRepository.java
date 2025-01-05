package com.java.ravito_plan.food.infrastructure.adapters.repository;

import com.java.ravito_plan.food.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedBrandRepository extends JpaRepository<Brand, Long> {

}
