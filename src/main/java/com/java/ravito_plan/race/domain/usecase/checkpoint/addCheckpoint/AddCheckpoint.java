package com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddCheckpoint implements AddCheckpointInterface {

    private final RaceRepository raceRepository;
    private final FoodPort foodPort;

    public AddCheckpoint(RaceRepository raceRepository, FoodPort foodPort) {
        this.raceRepository = raceRepository;
        this.foodPort = foodPort;
    }

    @Override
    @Transactional
    public void execute(AddCheckpointRequest request, AddCheckpointPresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        race.addOrUpdateCheckpoint(CheckpointMapper.toCheckpoint(request.command()));
        Race updatedRace = this.raceRepository.save(race);

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(updatedRace));

        presenter.present(new AddCheckpointResponse(updatedRace, foods));
    }

    private Set<Long> getAllFoodIdsForRace(Race race) {
        return race.getCheckpoints().stream().flatMap(cp -> cp.getCheckpointFoods().stream())
                .map(CheckpointFood::getFoodId).collect(Collectors.toSet());
    }
}
