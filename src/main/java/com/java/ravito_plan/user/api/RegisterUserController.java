package com.java.ravito_plan.user.api;

import com.java.ravito_plan.user.application.dto.auth.RegisterUserCommand;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUser;
import com.java.ravito_plan.user.domain.usecase.registerUser.RegisterUserRequest;
import com.java.ravito_plan.user.infrastructure.presenter.registerUser.RegisterUserJsonPresenter;
import com.java.ravito_plan.user.infrastructure.presenter.registerUser.RegisterUserViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterUserController {

    private final RegisterUser usecase;
    private final RegisterUserJsonPresenter presenter;

    public RegisterUserController(RegisterUser usecase, RegisterUserJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<RegisterUserViewModel> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        this.usecase.execute(new RegisterUserRequest(command.getUsername(), command.getPassword(),
                command.getUsername()), this.presenter);

        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
