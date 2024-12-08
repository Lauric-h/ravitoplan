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
        return new UserDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserDto getByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow();
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }
}
