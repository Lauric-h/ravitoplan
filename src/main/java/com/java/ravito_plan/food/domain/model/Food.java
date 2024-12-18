package com.java.ravito_plan.food.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false, name = "brand_id")
    private Brand brand;

    @Column
    private int carbohydrates;

    @Column
    private int calories;

    @Column
    private int proteins;

    @ColumnDefault("false")
    private boolean electrolytes;

    @Column
    private String link;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    private IngestionType ingestionType;
}
