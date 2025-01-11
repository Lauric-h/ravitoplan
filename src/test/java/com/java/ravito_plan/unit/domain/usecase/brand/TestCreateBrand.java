package com.java.ravito_plan.unit.domain.usecase.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandImpl;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.createBrand.CreateBrandResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestCreateBrand implements CreateBrandPresenter {

  @Autowired
  @Qualifier("inMemoryBrandRepository")
  private InMemoryBrandRepository brandRepository;

  private CreateBrandImpl createBrand;

  private CreateBrandResponse response;

  @Override
  public void present(CreateBrandResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.createBrand = new CreateBrandImpl(brandRepository);
  }

  @Test
  public void test_createBrand() {
    Brand expected = new Brand("Test Brand");
    expected.setId(1L);
    CreateBrandRequest request = new CreateBrandRequest("Test Brand");

    this.createBrand.execute(request, this);

    assertThat(this.response.brand()).usingRecursiveComparison().isEqualTo(expected);
  }
}
