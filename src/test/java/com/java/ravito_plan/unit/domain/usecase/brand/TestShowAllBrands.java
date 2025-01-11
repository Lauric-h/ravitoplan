package com.java.ravito_plan.unit.domain.usecase.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsImpl;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsRequest;
import com.java.ravito_plan.food.domain.usecase.brand.showAllBrands.ShowAllBrandsResponse;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowAllBrands implements ShowAllBrandsPresenter {

  @Autowired
  private InMemoryBrandRepository brandRepository;

  private ShowAllBrandsResponse response;

  private ShowAllBrandsImpl showAllBrands;

  @BeforeEach
  public void setUp() {
    this.brandRepository.clear();
    this.showAllBrands = new ShowAllBrandsImpl(brandRepository);
  }

  @Override
  public void present(ShowAllBrandsResponse response) {
    this.response = response;
  }

  @Test
  public void test_showAllBrands() {
    Brand brand1 = new Brand("Test Brand 1");
    Brand brand2 = new Brand("Test Brand 2");
    brand1.setId(1L);
    brand2.setId(2L);
    this.brandRepository.addBrand(brand1);
    this.brandRepository.addBrand(brand2);

    this.showAllBrands.execute(new ShowAllBrandsRequest(), this);

    assertThat(this.response.brands().size()).isEqualTo(2);
  }
}
