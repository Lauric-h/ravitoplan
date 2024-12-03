package com.java.ravito_plan.user.domain.repository;

import com.java.ravito_plan.user.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
