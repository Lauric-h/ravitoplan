package com.java.ravito_plan.web.controllers.auth;

import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.dto.auth.RegisterRequest;
import com.java.ravito_plan.user.application.service.UserApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    private final UserApplicationService userApplicationService;

    @Autowired
    public RegistrationController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        RegisterRequest user = new RegisterRequest();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid RegisterRequest userRequest,
            Model model) {
        try {
            UserDto user = this.userApplicationService.registerUser(userRequest);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }
    }
}
