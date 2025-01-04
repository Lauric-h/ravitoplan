package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRaces;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesRequest;
import com.java.ravito_plan.race.infrastructure.presenter.race.showAllRaces.ShowAllRacesJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.race.showAllRaces.ShowAllRacesViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class ShowAllRacesController {

    private final ShowAllRaces usecase;
    private final ShowAllRacesJsonPresenter presenter;
    private final UserPort userPort;

    public ShowAllRacesController(ShowAllRaces usecase, ShowAllRacesJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @GetMapping()
    public ResponseEntity<ShowAllRacesViewModel> getUserRaces(@AuthenticationPrincipal UserDetails userDetails) {

        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new ShowAllRacesRequest(user.id), presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
