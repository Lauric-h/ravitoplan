package com.java.ravito_plan.food.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Food.class, mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name")
    private List<Food> foods = new ArrayList<>();

    public Brand(String name) {
        this.name = name;
    }

    public Brand updateFields(String name) {
        this.name = name;
        return this;
    }

    public Brand addOrUpdateFood(Food food) {
        Food existingFood = this.foods.stream().filter(f -> f.getName().equals(food.getName()))
                .findFirst().orElse(null);
        if (existingFood == null) {
            this.foods.add(food);
            food.setBrand(this);
        } else {
            existingFood.updateFields(food.getName(), food.getCarbohydrates(), food.getCalories(),
                    food.getProteins(), food.isElectrolytes(), food.getLink(), food.getComment(),
                    food.getIngestionType());
        }

        return this;
    }
}
