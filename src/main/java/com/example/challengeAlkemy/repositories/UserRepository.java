package com.example.challengeAlkemy.repositories;

import com.example.challengeAlkemy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
