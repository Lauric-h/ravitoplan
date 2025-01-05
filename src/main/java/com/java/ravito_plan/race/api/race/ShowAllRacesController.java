package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRaces;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesRequest;
import com.java.ravito_plan.race.infrastructure.presenter.race.showAllRaces.ShowAllRacesJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.race.showAllRaces.ShowAllRacesViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class ShowAllRacesController {

    private final ShowAllRaces usecase;
    private final ShowAllRacesJsonPresenter presenter;

    public ShowAllRacesController(ShowAllRaces usecase, ShowAllRacesJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping()
    public ResponseEntity<ShowAllRacesViewModel> getUserRaces(@CurrentUser RaceUserDto user) {
        this.usecase.execute(new ShowAllRacesRequest(user.id), presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
