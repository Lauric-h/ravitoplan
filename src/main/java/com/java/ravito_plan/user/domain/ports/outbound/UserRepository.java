package com.java.ravito_plan.user.domain.ports.outbound;

import com.java.ravito_plan.user.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    boolean existsByUsername(String username);
}
