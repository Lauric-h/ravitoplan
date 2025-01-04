package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRace;
import com.java.ravito_plan.race.infrastructure.presenter.race.createRace.CreateRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.race.createRace.CreateRaceViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/races")
public class CreateRaceController {

    private final CreateRace usecase;
    private final CreateRaceJsonPresenter presenter;
    private final UserPort userPort;

    public CreateRaceController(CreateRace usecase, CreateRaceJsonPresenter presenter,
            UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @PostMapping
    public ResponseEntity<CreateRaceViewModel> createRace(
            @Valid @RequestBody CreateRaceCommand command,
            @AuthenticationPrincipal UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(RaceMapper.toCreateRaceRequest(user.id, command), this.presenter);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.presenter.getViewModel());
    }
}
