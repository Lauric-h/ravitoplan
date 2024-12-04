package com.java.ravito_plan.user.domain.ports.outbound;

import com.java.ravito_plan.user.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
