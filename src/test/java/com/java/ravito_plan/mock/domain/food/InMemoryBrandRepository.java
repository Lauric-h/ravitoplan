package com.java.ravito_plan.mock.domain.food;

import com.java.ravito_plan.food.application.exception.BrandNotFoundException;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InMemoryBrandRepository implements BrandRepository {

  private final HashMap<Long, Brand> brands = new HashMap<>();

  public void addBrand(Brand brand) {
    this.brands.put(brand.getId(), brand);
  }

  @Override
  public Brand findById(Long id) {
    Brand brand = this.brands.get(id);
    if (brand == null) {
      throw new BrandNotFoundException(id);
    }
    return brand;
  }

  @Override
  public Brand save(Brand brand) {
    if (brand.getId() == null) {
      brand.setId((long) (this.brands.size() + 1));
    }

    Brand existingBrand = this.brands.putIfAbsent(brand.getId(), brand);
    if (existingBrand != null) {
      this.brands.replace(brand.getId(), brand);
      return existingBrand;
    }
    return this.brands.get(brand.getId());
  }

  @Override
  public List<Brand> findAll() {
    return new ArrayList<>(this.brands.values());
  }

  @Override
  public void deleteById(Long id) {
    this.brands.remove(id);
  }
}
