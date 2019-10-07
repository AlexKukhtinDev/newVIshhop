package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> create(User user);

    Optional<User> get(Long id);

    Optional<List<User>> getAll();

    Optional<User> update(User user);

    void delete(Long id);

    Optional<User> login(String login, String password);

    Optional<User> getByToken(String token);

    List<Order> getOrders(Long userId);

    Optional<User> addRole(Long userId, Long roleId);

    Optional<User> setUser(Long id, String name, String surname,
                           String login, String password, String token);
}
