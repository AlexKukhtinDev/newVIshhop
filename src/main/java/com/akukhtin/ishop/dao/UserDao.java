package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.User;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

public interface UserDao {
    User create(User item);

    User get(Long id);

    List<User> getAll();

    User update(User item);

    User delete(Long id);

    User deleteByUser(User item);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
