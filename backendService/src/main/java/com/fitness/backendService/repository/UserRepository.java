package com.fitness.backendService.repository;

import com.fitness.backendService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsById(String userId);

    User findByEmail(String email);
}
