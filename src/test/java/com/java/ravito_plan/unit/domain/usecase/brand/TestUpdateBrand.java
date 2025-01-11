package com.java.ravito_plan.unit.domain.usecase.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandImpl;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandPresenter;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandRequest;
import com.java.ravito_plan.food.domain.usecase.brand.updateBrand.UpdateBrandResponse;
import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestUpdateBrand implements UpdateBrandPresenter {

  @Autowired
  @Qualifier("inMemoryBrandRepository")
  private InMemoryBrandRepository brandRepository;

  private UpdateBrandResponse response;

  private UpdateBrandImpl updateBrand;

  @BeforeEach
  public void setUp() {
    this.updateBrand = new UpdateBrandImpl(this.brandRepository);
  }

  @Override
  public void present(UpdateBrandResponse response) {
    this.response = response;
  }

  @Test
  public void test_updateBrand() {
    Brand brand = new Brand("Before update");
    brand.setId(1L);
    this.brandRepository.addBrand(brand);

    this.updateBrand.execute(new UpdateBrandRequest(1L, "After update"), this);

    assertThat(this.response.brand().getName()).isEqualTo("Before update");
  }
}
