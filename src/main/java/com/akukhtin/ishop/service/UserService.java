package com.akukhtin.ishop.service;

import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

public interface UserService {

    List<Order> getOrders(Long userId);

    Optional<User> create(User user);

    Optional<User> get(Long id);

    Optional<List<User>> getAll();

    Optional<User> update(User user);

    void delete(Long id);

    Optional<User> login(String login, String password)
            throws AuthenticationException, com.akukhtin.ishop.exeptions.AuthenticationException;

    Optional<User> getByToken(String token);
}
