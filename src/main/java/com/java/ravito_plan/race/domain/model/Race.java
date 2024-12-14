package com.java.ravito_plan.race.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @OrderBy("distanceFromStart ASC")
    private List<Checkpoint> checkpoints = new ArrayList<>();

    public Race(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode) {
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;

        Checkpoint start = new Checkpoint("Start", 0, CheckpointType.START);
        Checkpoint finish = new Checkpoint("Finish", this.distance, CheckpointType.FINISH);

        this.addCheckpoint(start);
        this.addCheckpoint(finish);
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

    public Race addCheckpoint(Checkpoint checkpoint) {
        boolean checkpointExistsAtSameDistance = this.checkpoints.stream()
                .anyMatch(cp -> cp.getDistanceFromStart() == checkpoint.getDistanceFromStart());

        if (checkpointExistsAtSameDistance) {
            throw new IllegalArgumentException("Checkpoint already exists");
        }

        this.checkpoints.add(checkpoint);
        checkpoint.setRace(this);
        return this;
    }
}
