package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.updateRace.UpdateRaceInterface;
import com.java.ravito_plan.race.domain.usecase.updateRace.UpdateRaceRequest;
import com.java.ravito_plan.race.infrastructure.presenter.updateRace.UpdateRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.updateRace.UpdateRaceViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}")
public class UpdateRaceController {

    private final UpdateRaceJsonPresenter presenter;
    private final UpdateRaceInterface usecase;
    private final UserPort userPort;

    public UpdateRaceController(UpdateRaceJsonPresenter presenter, UpdateRaceInterface usecase,
            UserPort userPort) {
        this.presenter = presenter;
        this.usecase = usecase;
        this.userPort = userPort;
    }

    @PutMapping
    public ResponseEntity<UpdateRaceViewModel> updateRace(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails, UpdateRaceCommand command) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new UpdateRaceRequest(command, user.id), presenter);

        return ResponseEntity.ok(this.presenter.getViewModel());

    }
}
