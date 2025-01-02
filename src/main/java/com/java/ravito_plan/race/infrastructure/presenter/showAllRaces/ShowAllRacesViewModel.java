package com.java.ravito_plan.race.infrastructure.presenter.showAllRaces;

import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import java.util.List;

public record ShowAllRacesViewModel(List<RaceSummaryView> races) {

}
