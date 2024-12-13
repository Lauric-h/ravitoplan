package com.java.ravito_plan.race.application.service;


import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.model.Segment;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.SegmentRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;

public class SegmentApplicationService extends BaseApplicationService {

    private final SegmentRepository segmentRepository;

    public SegmentApplicationService(RaceRepository raceRepository, UserPort userPort, SegmentRepository segmentRepository) {
        super(raceRepository, userPort);
        this.segmentRepository = segmentRepository;
    }

    public void addSegmentToRace(Long raceId, CheckpointDto startDto, CheckpointDto finishDto) {
        this.verifyUserOwnsRace(raceId);

        Checkpoint start = new Checkpoint(startDto.name, startDto.distanceFromStart, startDto.location,
                CheckpointType.valueOf(startDto.type));
        Checkpoint finish = new Checkpoint(finishDto.name, finishDto.distanceFromStart, finishDto.location,
                CheckpointType.valueOf(finishDto.type));

        Segment segment = new Segment(start, finish);
        segment.setRaceId(raceId);

        Segment savedSegment = this.segmentRepository.save(segment);
    }
}
