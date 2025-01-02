package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.showRace.ShowRaceInterface;
import com.java.ravito_plan.race.domain.usecase.showRace.ShowRaceRequest;
import com.java.ravito_plan.race.infrastructure.presenter.showRace.ShowRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.showRace.ShowRaceViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}")
public class ShowRaceController {

    private final ShowRaceJsonPresenter presenter;
    private final ShowRaceInterface usecase;
    private final UserPort userPort;

    public ShowRaceController(ShowRaceJsonPresenter presenter, ShowRaceInterface usecase, UserPort userPort) {
        this.presenter = presenter;
        this.usecase = usecase;
        this.userPort = userPort;
    }

    @GetMapping
    public ResponseEntity<ShowRaceViewModel> showRace(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new ShowRaceRequest(user.id, id), presenter);
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
