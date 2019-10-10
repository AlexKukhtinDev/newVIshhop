package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.exeptions.AuthenticationException;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    Optional<User> update(User user);

    void delete(Long id);

    Optional<User> login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    List<Order> getOrders(Long userId);

    Optional<User> addRole(Long userId, Long roleId);
}
