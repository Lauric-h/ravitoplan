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

    @Column
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckpointType type;

    @ManyToOne
    private Race race;

    @Column
    private Integer estimatedTimeInMinuteFromStart;

    @Column(nullable = false)
    private int cumulatedElevationGainFromStart;

    @Column(nullable = false)
    private int cumulatedElevationLossFromStart;

    @Column
    private Integer carbsTarget;

    public Checkpoint(String name, int distanceFromStart, CheckpointType type, int cumulatedElevationGainFromStart, int cumulatedElevationLossFromStart) {
        this.name = name;
        this.distanceFromStart = distanceFromStart;
        this.type = type;
        this.cumulatedElevationGainFromStart = cumulatedElevationGainFromStart;
        this.cumulatedElevationLossFromStart = cumulatedElevationLossFromStart;
    }

    public Checkpoint(String name, int distanceFromStart, String location, CheckpointType type,int cumulatedElevationGainFromStart, int cumulatedElevationLossFromStart, int estimatedTimeInMinuteFromStart, int carbsTarget) {
        this.name = name;
        this.distanceFromStart = distanceFromStart;
        this.location = location;
        this.type = type;
        this.cumulatedElevationGainFromStart = cumulatedElevationGainFromStart;
        this.cumulatedElevationLossFromStart = cumulatedElevationLossFromStart;
        this.estimatedTimeInMinuteFromStart = estimatedTimeInMinuteFromStart;
        this.carbsTarget = carbsTarget;
    }

    public Checkpoint updateDetails(Checkpoint checkpoint) {
        this.name = checkpoint.getName();
        this.distanceFromStart = checkpoint.getDistanceFromStart();
        this.type = checkpoint.getType();
        this.location = checkpoint.getLocation();
        this.cumulatedElevationGainFromStart = checkpoint.getCumulatedElevationGainFromStart();
        this.cumulatedElevationLossFromStart = checkpoint.getCumulatedElevationLossFromStart();
        this.carbsTarget = checkpoint.getCarbsTarget();
        this.estimatedTimeInMinuteFromStart = checkpoint.getEstimatedTimeInMinuteFromStart();

        return this;
    }
}
