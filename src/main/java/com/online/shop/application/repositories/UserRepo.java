package com.online.shop.application.repositories;

import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    User findUserById(long id);

    @Query(value = "SELECT o.* FROM USER u "
            + "JOIN \"order\" o on o.user_id = u.id "
            + "where u.id=?1",
            nativeQuery = true)
    List<Order> getUserOrders(long userId);
}
