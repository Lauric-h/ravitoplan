package com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.CheckpointFactory;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddCheckpointImpl implements AddCheckpoint {

    private final RaceRepository raceRepository;
    private final FoodPort foodPort;
    private final CheckpointFactory checkpointFactory;

    public AddCheckpointImpl(RaceRepository raceRepository, FoodPort foodPort,
            CheckpointFactory checkpointFactory) {
        this.raceRepository = raceRepository;
        this.foodPort = foodPort;
        this.checkpointFactory = checkpointFactory;
    }

    @Override
    @Transactional
    public void execute(AddCheckpointRequest request, AddCheckpointPresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        race.addOrUpdateCheckpoint(this.checkpointFactory.create(request.checkpointParams()));
        Race updatedRace = this.raceRepository.save(race);

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(updatedRace.getAllFoodIds());

        presenter.present(new AddCheckpointResponse(updatedRace, foods));
    }
}
