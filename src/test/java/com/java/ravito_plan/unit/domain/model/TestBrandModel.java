package com.java.ravito_plan.unit.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.food.domain.model.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestBrandModel {

  @Test
  public void test_updateFields() {
    Brand brand = new Brand("Before udpate");
    brand.updateFields("After udpate");

    assertThat(brand.getName()).isEqualTo("After udpate");
  }
}
