package com.java.ravito_plan.race.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Segment {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int distance;

    @Column(nullable = false)
    private int elevationPositive;

    @Column(nullable = false)
    private int elevationNegative;

    @Column(nullable = false)
    private int estimatedMinuteTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private Checkpoint start;

    @ManyToOne(cascade = CascadeType.ALL)
    private Checkpoint finish;

    @Column(nullable = false)
    private Long raceId;

    public Segment(Checkpoint start, Checkpoint finish) {
        if (finish.getDistanceFromStart() <= start.getDistanceFromStart()) {
            throw new IllegalArgumentException("Finish checkpoint must be after start checkpoint.");
        }

        this.start = start;
        this.finish = finish;

        this.distance = finish.getDistanceFromStart() - start.getDistanceFromStart();
    }
}
