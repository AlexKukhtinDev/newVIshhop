package com.akukhtin.ishop.dao.impl;

import com.akukhtin.ishop.dao.Storage;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.AuthenticationException;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find number with id " + id));
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException("Can't find user" + user.getName());
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        Storage.users.removeIf(s -> s.getId().equals(id));
        return user;
    }

    @Override
    public User deleteByUser(User user) {
        Storage.users.removeIf(s -> s.equals(user));
        return user;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = Storage.users
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect username or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users
                .stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
