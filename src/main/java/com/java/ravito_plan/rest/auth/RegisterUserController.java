package com.java.ravito_plan.rest.auth;

import com.java.ravito_plan.rest.view.auth.RegisterUserRequest;
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
    public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            this.userApplicationService.registerUser(registerUserRequest.username,
                    registerUserRequest.email, registerUserRequest.password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
