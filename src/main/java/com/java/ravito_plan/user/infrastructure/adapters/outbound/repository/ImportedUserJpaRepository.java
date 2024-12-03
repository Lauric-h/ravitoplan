package com.java.ravito_plan.user.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedUserJpaRepository extends JpaRepository<User, Long> {

}
