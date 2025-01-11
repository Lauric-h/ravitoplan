package com.java.ravito_plan.unit.domain.usecase.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.application.exception.BrandNotFoundException;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandImpl;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.showBrand.ShowBrandResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowBrand implements ShowBrandPresenter {

  @Autowired
  @Qualifier("inMemoryBrandRepository")
  private InMemoryBrandRepository brandRepository;

  private ShowBrandImpl showBrand;

  private ShowBrandResponse response;

  @Override
  public void present(ShowBrandResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.showBrand = new ShowBrandImpl(this.brandRepository);
  }

  @Test
  public void test_showBrand() {
    Brand expected = new Brand("Test brand");
    expected.setId(1L);
    this.brandRepository.addBrand(expected);
    ShowBrandRequest request = new ShowBrandRequest(1L);

    this.showBrand.execute(request, this);

    assertThat(this.response.brand()).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void test_showBrand_not_found_throws_exception() {
    ShowBrandRequest request = new ShowBrandRequest(1L);

    Assertions.assertThatExceptionOfType(BrandNotFoundException.class)
        .isThrownBy(() -> this.showBrand.execute(request, this));
  }
}
