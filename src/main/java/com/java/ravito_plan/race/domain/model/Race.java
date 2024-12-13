package com.java.ravito_plan.race.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int distance;

    @Column(nullable = false)
    private int elevationPositive;

    @Column(nullable = false)
    private int elevationNegative;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segment> segments;

    public Race(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode) {
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Race updateFields(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode) {
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;

        return this;
    }
}
