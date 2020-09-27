package com.online.shop.application.repositories;

import com.online.shop.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    User findUserById(long id);
}
