package com.akukhtin.ishop.service.impl;

import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.lib.Service;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;
import com.akukhtin.ishop.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public Optional<List<Order>> getOrders(Long userId) {
        return userDao.getOrders(userId);
    }

    @Override
    public Optional<User> create(User user) {
        user.setToken(getToken());
        return userDao.create(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Optional<User> get(Long id) {
        return userDao.get(id);
    }

    @Override
    public Optional<List<User>> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public Optional<User> login(String login, String password)
            throws AuthenticationException, com.akukhtin.ishop.exeptions.AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }
}
