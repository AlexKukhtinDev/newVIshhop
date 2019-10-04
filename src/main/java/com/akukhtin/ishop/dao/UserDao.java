package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

public interface UserDao {
    Optional<User> create(User user);

    Optional<User> get(Long id);

    Optional<List<User>> getAll();

    Optional<User> update(User user);

    void delete(Long id);

    default Optional<User> login(String login, String password)
            throws AuthenticationException, com.akukhtin.ishop.exeptions.AuthenticationException {
        return Optional.empty();
    }

    Optional<User> getByToken(String token);

    Optional<List<Order>> getOrders(Long userId);
}
