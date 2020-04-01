package com.rettiwt.rettiwt.repository;

import com.rettiwt.rettiwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
