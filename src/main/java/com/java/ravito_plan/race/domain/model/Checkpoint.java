package com.java.ravito_plan.race.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkpoint {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int distanceFromStart;

    @Column()
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckpointType type;

    @ManyToOne()
    private Race race;

    public Checkpoint(String name, int distanceFromStart, CheckpointType type) {
        this.name = name;
        this.distanceFromStart = distanceFromStart;
        this.type = type;
    }

    public Checkpoint(String name, int distanceFromStart, String location, CheckpointType type) {
        this.name = name;
        this.distanceFromStart = distanceFromStart;
        this.location = location;
        this.type = type;
    }

    public Checkpoint updateDetails(Checkpoint checkpoint) {
        this.name = checkpoint.getName();
        this.distanceFromStart = checkpoint.getDistanceFromStart();
        this.type = checkpoint.getType();
        this.location = checkpoint.getLocation();
        return this;
    }
}
