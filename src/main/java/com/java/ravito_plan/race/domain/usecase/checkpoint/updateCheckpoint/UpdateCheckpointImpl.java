package com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateCheckpointImpl implements UpdateCheckpoint {

    private final CheckpointRepository checkpointRepository;
    private final RaceRepository raceRepository;
    private final FoodPort foodPort;

    public UpdateCheckpointImpl(CheckpointRepository checkpointRepository,
            RaceRepository raceRepository, FoodPort foodPort) {
        this.checkpointRepository = checkpointRepository;
        this.raceRepository = raceRepository;
        this.foodPort = foodPort;
    }

    @Override
    @Transactional
    public void execute(UpdateCheckpointRequest request, UpdateCheckpointPresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        Checkpoint checkpoint = checkpointRepository.findByIdAndRaceId(request.checkpointId(), request.raceId());
        checkpoint.updateDetails(CheckpointMapper.toCheckpoint(request.command()));

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(savedCheckpoint.getRace()));

        presenter.present(new UpdateCheckpointResponse(savedCheckpoint.getRace(), foods));
    }

    private Set<Long> getAllFoodIdsForRace(Race race) {
        return race.getCheckpoints().stream().flatMap(cp -> cp.getCheckpointFoods().stream())
                .map(CheckpointFood::getFoodId).collect(Collectors.toSet());
    }
}
