package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.SegmentDto;
import com.java.ravito_plan.race.domain.model.Segment;

public class SegmentMapper {

    public static SegmentDto toSegmentDto(Segment segment) {
        return new SegmentDto(segment.getStart().getDistanceFromStart(),
                segment.getFinish().getDistanceFromStart(), segment.getDistance(),
                segment.getEstimatedTime(), segment.getElevationGain(), segment.getElevationLoss());
    }
}
