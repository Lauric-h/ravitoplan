package com.java.ravito_plan.race.domain.usecase.race.showFullRace;

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
public class ShowFullRaceImpl implements ShowFullRace {

    private final RaceRepository raceRepository;
    private final FoodPort foodPort;

    public ShowFullRaceImpl(RaceRepository raceRepository, FoodPort foodPort) {
        this.raceRepository = raceRepository;
        this.foodPort = foodPort;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(ShowFullRaceRequest request, ShowFullRacePresenter presenter) {
        Race race = this.raceRepository.findByIdAndUserId(request.raceId(), request.userId());

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(race.getAllFoodIds());

        presenter.present(new ShowFullRaceResponse(race, foods));
    }
}
