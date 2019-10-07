package com.akukhtin.ishop.dao.impl;

import com.akukhtin.ishop.dao.Storage;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.AuthenticationException;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> create(User user) {
        Storage.users.add(user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(Storage.users);
    }

    @Override
    public Optional<User> update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
                return Optional.of(user);
            }
        }
        throw new NoSuchElementException("Can't find user" + user.getName());
    }

    @Override
    public void delete(Long id) {
        Storage.users.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public Optional<User> login(String login, String password) throws AuthenticationException {
        Optional<User> user = Storage.users
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect username or password");
        }
        return user;
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users
                .stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }

    @Override
    public Optional<List<Order>> getOrders(Long userId) {
        return null;
    }

    @Override
    public Optional<User> addRole(Long userId, Long roleId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> setUser(Long id, String name, String surname,
                                  String login, String password, String token) {
        return Optional.empty();
    }
}
