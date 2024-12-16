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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    private Race(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode) {
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.elevationPositive = elevationPositive;
        this.elevationNegative = elevationNegative;
        this.city = city;
        this.postalCode = postalCode;
    }

    public static Race create(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode) {
        Race race = new Race(name, date, distance, elevationPositive, elevationNegative, city,
                postalCode);

        race.addDefaultCheckpoints();
        race.validate();

        return race;
    }

    public void validate() {
        this.validateCheckpointExist();
        this.validateStartAndFinishCheckpoints();
        this.validateElevationConsistency();
        this.validateCheckpointOrder();
    }

    private void validateCheckpointExist() {
        if (this.checkpoints == null || this.checkpoints.isEmpty()) {
            throw new IllegalArgumentException(
                    "Race must have at least two checkpoints: start and finish.");
        }
    }

    private boolean hasCheckpointAtDistance(int distance) {
        return this.checkpoints.stream().anyMatch(cp -> cp.getDistanceFromStart() == distance);
    }

    private void validateStartAndFinishCheckpoints() {
        boolean hasStartCheckpoint = this.hasCheckpointAtDistance(0);
        boolean hasFinishCheckpoint = this.hasCheckpointAtDistance(this.distance);

        if (!hasStartCheckpoint || !hasFinishCheckpoint) {
            throw new IllegalArgumentException("Race must have a starting and finish checkpoint");
        }
    }

    private void validateElevationConsistency() {
        Checkpoint start = this.getStartCheckpoint();
        if (start.getCumulatedElevationGainFromStart() != 0
                || start.getCumulatedElevationLossFromStart() != 0) {
            throw new IllegalArgumentException(String.format("start %s -%s", start.getCumulatedElevationGainFromStart(), start.getCumulatedElevationLossFromStart()));
        }

        Checkpoint finish = this.getFinishCheckpoint();
        if (finish.getCumulatedElevationGainFromStart() != this.elevationPositive
                || finish.getCumulatedElevationLossFromStart() != this.elevationNegative) {
            throw new IllegalArgumentException(String.format("finish %s -%s", finish.getCumulatedElevationGainFromStart(), finish.getCumulatedElevationLossFromStart()));
        }
    }

    public Checkpoint getStartCheckpoint() {
        return this.checkpoints.get(0);
    }

    public Checkpoint getFinishCheckpoint() {
        return this.checkpoints.get(this.checkpoints.size() - 1);
    }

    private void validateCheckpointOrder() {
        for (int i = 1; i < checkpoints.size(); i++) {
            if (this.checkpoints.get(i).getDistanceFromStart() <= this.checkpoints.get(i - 1)
                    .getDistanceFromStart()) {
                throw new IllegalArgumentException(
                        "Checkpoints must be in increasing distance order");
            }
        }
    }

    private void addDefaultCheckpoints() {
        Checkpoint start = new Checkpoint("Start", 0, CheckpointType.START, 0, 0);
        Checkpoint finish = new Checkpoint("Finish", this.distance, CheckpointType.FINISH,
                this.elevationPositive, this.elevationNegative);

        this.checkpoints.clear();
        this.addOrUpdateCheckpoint(start);
        this.addOrUpdateCheckpoint(finish);
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

    public Race addOrUpdateCheckpoint(Checkpoint checkpoint) {
        if (checkpoint.getDistanceFromStart() > this.getDistance()) {
            throw new IllegalArgumentException();
        }

        Checkpoint existingCheckpoint = this.checkpoints.stream()
                .filter(cp -> cp.getDistanceFromStart() == checkpoint.getDistanceFromStart())
                .findFirst().orElse(null);

        if (existingCheckpoint != null) {
            existingCheckpoint.updateDetails(checkpoint);
        } else {
            this.checkpoints.add(checkpoint);
            checkpoint.setRace(this);
        }

        return this;
    }

    public void removeCheckpoint(Checkpoint checkpoint) {
        this.checkpoints.remove(checkpoint);
        this.validate();
    }

    public List<Segment> getSegments() {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < this.checkpoints.size() - 1; i++) {
            Checkpoint start = this.checkpoints.get(i);
            Checkpoint finish = this.checkpoints.get(i + 1);
            segments.add(new Segment(start, finish));
        }

        return segments;
    }
}
