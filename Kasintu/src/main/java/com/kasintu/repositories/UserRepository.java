package com.kasintu.repositories;

import com.kasintu.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
