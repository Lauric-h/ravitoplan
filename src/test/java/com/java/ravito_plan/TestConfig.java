package com.java.ravito_plan;

import com.java.ravito_plan.mock.domain.food.InMemoryBrandRepository;
import com.java.ravito_plan.mock.domain.food.InMemoryFoodRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointFoodRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryCheckpointRepository;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
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

    @Bean
    @Primary
    public InMemoryRaceRepository inMemoryRaceRepository() {
        return new InMemoryRaceRepository();
    }

    @Bean
    @Primary
    public InMemoryCheckpointRepository inMemoryCheckpointRepository() {
        return new InMemoryCheckpointRepository();
    }

    @Bean
    @Primary
    public InMemoryCheckpointFoodRepository inMemoryCheckpointFoodRepository() {
        return new InMemoryCheckpointFoodRepository();
    }
}
