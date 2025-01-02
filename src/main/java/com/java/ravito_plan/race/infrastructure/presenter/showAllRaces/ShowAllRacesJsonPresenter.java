package com.java.ravito_plan.race.infrastructure.presenter.showAllRaces;

import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.showAllRaces.ShowAllRacesPresenter;
import com.java.ravito_plan.race.domain.usecase.showAllRaces.ShowAllRacesResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ShowAllRacesJsonPresenter implements ShowAllRacesPresenter {

    private List<RaceSummaryView> racesViewModel;

    @Override
    public void present(ShowAllRacesResponse response) {
        this.racesViewModel = response.races().stream().map(RaceViewMapper::toRaceSummaryView)
                .toList();
    }

    public List<RaceSummaryView> getViewModel() {
        return this.racesViewModel;
    }
}
