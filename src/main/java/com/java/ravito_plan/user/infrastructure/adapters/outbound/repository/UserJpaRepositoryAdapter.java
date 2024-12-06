package com.java.ravito_plan.user.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.user.domain.ports.outbound.UserRepository;
import com.java.ravito_plan.user.domain.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserJpaRepositoryAdapter implements UserRepository {

    private final ImportedUserJpaRepository repository;

    public UserJpaRepositoryAdapter(ImportedUserJpaRepository importedUserJpaRepository) {
        this.repository = importedUserJpaRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return this.repository.save(user);
    }
}
