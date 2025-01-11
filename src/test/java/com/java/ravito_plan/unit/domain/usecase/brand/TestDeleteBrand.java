package com.java.ravito_plan.unit.domain.usecase.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandImpl;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.deleteBrand.DeleteBrandResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestDeleteBrand implements DeleteBrandPresenter {

    @Autowired
    @Qualifier("inMemoryBrandRepository")
    private InMemoryBrandRepository brandRepository;

    private DeleteBrandImpl deleteBrand;

    @BeforeEach
    public void setUp() {
        this.deleteBrand = new DeleteBrandImpl(brandRepository);
    }

    @Override
    public void present(DeleteBrandResponse response) {
        new DeleteBrandResponse();
    }

    @Test
    public void test_deleteBrand() {
        Brand brand = new Brand("Test brand");
        brand.setId(1L);
        this.brandRepository.addBrand(brand);

        DeleteBrandRequest request = new DeleteBrandRequest(1L);
        this.deleteBrand.execute(request, this);

        assertThat(this.brandRepository.findAll().size()).isEqualTo(0);
    }
}
