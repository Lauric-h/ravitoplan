package com.java.ravito_plan.race.infrastructure.presenter.race.showFullRace;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRaceResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ShowFullRaceJsonPresenter implements ShowFullRacePresenter {

    private ShowFullRaceViewModel viewModel;

    @Override
    public void present(ShowFullRaceResponse response) {
        this.viewModel = new ShowFullRaceViewModel(RaceViewMapper.toRaceDetailView(response.race(), response.foods()));
    }
}
