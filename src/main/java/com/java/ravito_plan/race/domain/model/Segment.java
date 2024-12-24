package com.java.ravito_plan.race.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Segment {
    private final Checkpoint start;
    private final Checkpoint finish;
    private final int distance;
    @Setter
    private Integer estimatedTime;
    private final int elevationGain;
    private final int elevationLoss;

    public Segment(Checkpoint start, Checkpoint finish) {
        if (finish.getDistanceFromStart() < start.getDistanceFromStart()
                || finish.getCumulatedElevationGainFromStart()
                < start.getCumulatedElevationGainFromStart()
                || finish.getCumulatedElevationLossFromStart()
                < start.getCumulatedElevationLossFromStart()) {
            throw new IllegalArgumentException();
        }

        if (null != finish.getEstimatedTimeInMinuteFromStart()
                && null != start.getEstimatedTimeInMinuteFromStart()) {
            this.estimatedTime = finish.getEstimatedTimeInMinuteFromStart()
                    - start.getEstimatedTimeInMinuteFromStart();
        }

        this.start = start;
        this.finish = finish;
        this.distance = finish.getDistanceFromStart() - start.getDistanceFromStart();
        this.elevationGain = finish.getCumulatedElevationGainFromStart()
                - start.getCumulatedElevationGainFromStart();
        this.elevationLoss = finish.getCumulatedElevationLossFromStart()
                - start.getCumulatedElevationLossFromStart();
    }
}
