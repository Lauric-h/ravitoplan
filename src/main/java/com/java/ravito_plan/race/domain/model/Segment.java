package com.java.ravito_plan.race.domain.model;

import com.java.ravito_plan.race.domain.exception.CannotCreateSegmentWitCheckpointException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Segment {

    private final Checkpoint start;
    private final Checkpoint finish;
    private final int distance;
    private final int elevationGain;
    private final int elevationLoss;
    @Setter
    private Integer estimatedTime;

    public Segment(Checkpoint start, Checkpoint finish) {
        if (finish.getDistanceFromStart() < start.getDistanceFromStart()
                || finish.getCumulatedElevationGainFromStart()
                < start.getCumulatedElevationGainFromStart()
                || finish.getCumulatedElevationLossFromStart()
                < start.getCumulatedElevationLossFromStart()) {
            throw new CannotCreateSegmentWitCheckpointException(
                    String.format("Start %s, finish %s", start.getId(), finish.getId()));
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
