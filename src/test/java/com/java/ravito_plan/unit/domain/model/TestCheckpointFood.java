package com.java.ravito_plan.unit.domain.model;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.race.domain.exception.CheckpointFoodQuantityIsNegativeException;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestCheckpointFood {

	@Test
	public void test_setQuantity_negative_throws_exception() {
		CheckpointFood checkpointFood = new CheckpointFood();
		int quantity = -10;

		Assertions.assertThrows(CheckpointFoodQuantityIsNegativeException.class, () -> {checkpointFood.setQuantity(quantity);});
	}

	@Test
	public void test_setQuantity_zero_throws_exception() {
		CheckpointFood checkpointFood = new CheckpointFood();
		int quantity = 0;

		Assertions.assertThrows(CheckpointFoodQuantityIsNegativeException.class, () -> {checkpointFood.setQuantity(quantity);});
	}

	@Test
	public void test_setQuantity_positive_OK() {
		CheckpointFood checkpointFood = new CheckpointFood();
		int quantity = 2;

		CheckpointFood updated = checkpointFood.setQuantity(quantity);
		Assertions.assertEquals(quantity, updated.getQuantity());
	}
}
