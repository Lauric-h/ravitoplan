package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import com.java.ravito_plan.race.domain.usecase.createRace.CreateRaceInterface;
import com.java.ravito_plan.race.domain.usecase.createRace.CreateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.createRace.CreateRaceResponse;
import com.java.ravito_plan.race.infrastructure.presenter.createRace.CreateRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.createRace.CreateRaceViewModel;
import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
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

    private final CreateRaceInterface usecase;
    private final CreateRaceJsonPresenter presenter;
    private final UserPort userPort;

    public CreateRaceController(CreateRaceInterface usecase, CreateRaceJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @PostMapping
    public ResponseEntity<CreateRaceViewModel> createRace(@Valid @RequestBody CreateRaceCommand command, @AuthenticationPrincipal UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new CreateRaceRequest(user.id, command), this.presenter);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.presenter.getViewModel());
    }
}
