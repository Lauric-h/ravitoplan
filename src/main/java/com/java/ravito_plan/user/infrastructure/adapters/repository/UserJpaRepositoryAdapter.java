package com.java.ravito_plan.user.infrastructure.adapters.repository;

import com.java.ravito_plan.user.application.exception.UserNotFoundException;
import com.java.ravito_plan.user.domain.ports.UserRepository;
import com.java.ravito_plan.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserJpaRepositoryAdapter implements UserRepository {

    private final ImportedUserJpaRepository repository;

    public UserJpaRepositoryAdapter(ImportedUserJpaRepository importedUserJpaRepository) {
        this.repository = importedUserJpaRepository;
    }

    @Override
    public User findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User findByUsername(String username) {
       return this.repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.repository.existsByUsername(username);
    }

    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        return this.repository.existsByUsernameOrEmail(username, email);
    }
}
