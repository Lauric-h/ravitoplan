package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import com.java.ravito_plan.race.domain.usecase.showAllRaces.ShowAllRacesInterface;
import com.java.ravito_plan.race.domain.usecase.showAllRaces.ShowAllRacesRequest;
import com.java.ravito_plan.race.infrastructure.presenter.showAllRaces.ShowAllRacesJsonPresenter;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class ShowAllRacesController {

    private final ShowAllRacesInterface usecase;
    private final ShowAllRacesJsonPresenter presenter;
    private final UserPort userPort;

    public ShowAllRacesController(ShowAllRacesInterface usecase, ShowAllRacesJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @GetMapping()
    public ResponseEntity<List<RaceSummaryView>> getUserRaces(@AuthenticationPrincipal UserDetails userDetails) {

        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new ShowAllRacesRequest(user.id), presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
