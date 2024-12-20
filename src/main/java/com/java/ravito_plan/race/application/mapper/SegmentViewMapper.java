package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.view.SegmentView;
import com.java.ravito_plan.race.domain.model.Segment;

public class SegmentViewMapper {

    public static SegmentView toSegmentView(Segment segment) {
        return new SegmentView(segment.getStart().getDistanceFromStart(),
                segment.getFinish().getDistanceFromStart(), segment.getDistance(),
                segment.getEstimatedTime(), segment.getElevationGain(), segment.getElevationLoss());
    }
}
