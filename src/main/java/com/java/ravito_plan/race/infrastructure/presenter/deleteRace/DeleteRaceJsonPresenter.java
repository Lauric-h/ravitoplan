package com.java.ravito_plan.race.infrastructure.presenter.deleteRace;

import com.java.ravito_plan.race.domain.usecase.deleteRace.DeleteRacePresenter;
import com.java.ravito_plan.race.domain.usecase.deleteRace.DeleteRaceResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DeleteRaceJsonPresenter implements DeleteRacePresenter {

    private DeleteRaceViewModel viewModel;

    @Override
    public void present(DeleteRaceResponse response) {
       this.viewModel = new DeleteRaceViewModel();
    }
}
