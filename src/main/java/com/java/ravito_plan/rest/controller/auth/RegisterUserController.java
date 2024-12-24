package com.java.ravito_plan.rest.controller.auth;

import com.java.ravito_plan.user.application.dto.auth.RegisterUserCommand;
import com.java.ravito_plan.user.application.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

    private final UserApplicationService userApplicationService;

    @Autowired
    public RegisterUserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<Object> registerUser(
            @RequestBody RegisterUserCommand registerUserCommand) {
        this.userApplicationService.registerUser(registerUserCommand);
        return ResponseEntity.ok().build();
    }
}
