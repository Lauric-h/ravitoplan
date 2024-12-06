package com.java.ravito_plan.user.application.service;

import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.dto.auth.RegisterRequest;
import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
import com.java.ravito_plan.user.domain.service.UserDomainService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    private final UserRepository userRepository;
    private final UserDomainService userService;

    @Autowired
    public UserApplicationService(UserRepository userRepository, UserDomainService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public UserDto registerUser(RegisterRequest userRequest) {
        if (this.userRepository.findByUsername(userRequest.username).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Username %s already exists", userRequest.username));
        }

        if (this.userRepository.findByEmail(userRequest.email).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Email %s already exists", userRequest.email));
        }

        User user = this.userService.createUserWithHashedPassword(userRequest.email,
                userRequest.username, userRequest.password);

        User savedUser = this.userRepository.save(user);
        return new UserDto(savedUser.getUsername(), savedUser.getEmail());
    }

    public UserDto getUserById(Long id) {
        User user = this.userRepository.getById(id).orElseThrow();
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
