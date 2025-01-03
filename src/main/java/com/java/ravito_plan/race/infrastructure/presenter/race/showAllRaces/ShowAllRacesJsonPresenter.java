package com.java.ravito_plan.race.infrastructure.presenter.race.showAllRaces;

import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesPresenter;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ShowAllRacesJsonPresenter implements ShowAllRacesPresenter {

    private ShowAllRacesViewModel viewModel;

    @Override
    public void present(ShowAllRacesResponse response) {
        this.viewModel = new ShowAllRacesViewModel(
                response.races().stream().map(RaceViewMapper::toRaceSummaryView).toList());
    }
}
