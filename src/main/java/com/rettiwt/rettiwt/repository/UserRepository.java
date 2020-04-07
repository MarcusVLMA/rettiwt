package com.rettiwt.rettiwt.repository;

import java.util.Optional;

import com.rettiwt.rettiwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
