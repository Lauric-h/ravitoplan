package com.java.ravito_plan.food.infrastructure.adapters.repository;

import com.java.ravito_plan.food.application.exception.BrandNotFoundException;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.ports.repository.BrandRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandJpaRepositoryAdapter implements BrandRepository {
    private final ImportedBrandRepository repository;

    @Autowired
    public BrandJpaRepositoryAdapter(final ImportedBrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Brand findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BrandNotFoundException(id));
    }

    @Override
    public Brand save(Brand brand) {
        return this.repository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
