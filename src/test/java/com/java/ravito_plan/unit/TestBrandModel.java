package com.java.ravito_plan.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.food.domain.model.Brand;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBrandModel {

    @Test
    public void testUpdateFields() {
        var originalName = "Test Brand";
        var updatedName = "Updated Brand";

        Brand brand = Generator.getBrand(1L, originalName, Collections.emptyList());
        Brand expected = Generator.getBrand(1L, updatedName, Collections.emptyList());

        Brand actual = brand.updateFields(updatedName);

        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}
