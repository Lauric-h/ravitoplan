package com.java.ravito_plan;

import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public InMemoryBrandRepository inMemoryBrandRepository() {
        return new InMemoryBrandRepository();
    }

    @Bean
    @Primary
    public InMemoryFoodRepository inMemoryFoodRepository() {
        return new InMemoryFoodRepository();
    }
}
