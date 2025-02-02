package com.java.ravito_plan.race.infrastructure.presenter.race.showRace;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRaceResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShowRaceJsonPresenter implements ShowRacePresenter {

    private ShowRaceViewModel viewModel;

    @Override
    public void present(ShowRaceResponse response) {
        this.viewModel = new ShowRaceViewModel(RaceViewMapper.toRaceSummaryView(response.race()));
    }
}
