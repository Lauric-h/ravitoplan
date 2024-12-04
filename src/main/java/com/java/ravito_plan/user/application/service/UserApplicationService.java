package com.java.ravito_plan.user.application.service;

import com.java.ravito_plan.user.application.dto.UserDto;
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
    public User registerUser(UserDto userDto) {
        if (this.userRepository.findByUsername(userDto.username).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Username %s already exists", userDto.username));
        }

        if (this.userRepository.findByEmail(userDto.email).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Email %s already exists", userDto.email));
        }

        User user = this.userService.createUserWithHashedPassword(userDto.email, userDto.username,
                userDto.password);

        return this.userRepository.save(user);
    }
}
