package com.java.ravito_plan;

import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public InMemoryBrandRepository inMemoryBrandRepository() {
        return new InMemoryBrandRepository();
    }

    @Bean
    public InMemoryFoodRepository inMemoryFoodRepository() {
        return new InMemoryFoodRepository();
    }
}
