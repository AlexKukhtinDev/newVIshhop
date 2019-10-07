package com.akukhtin.ishop.dao.jdbc;

import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.RoleDao;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.Role;
import com.akukhtin.ishop.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Inject
    private static OrderDao orderDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> create(User user) {
        String query = "INSERT INTO `users` (`name`, `surname`, `login`, `password`, `token`)"
                + " VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create order");
        }

        for (Role role : user.getRoles()) {
            addRole(user.getId(), role.getId());
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users INNER JOIN users_roles"
                + " USING(user_id) WHERE user_id = ?;";
        User user = new User();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                Long roleId = resultSet.getLong("role_id");
                RoleDao roleDao = new RoleDaoJdbcImpl(connection);
                Optional<Role> role = roleDao.get(roleId);
                user = setUser(userId, name, surname, login, password, token).get();
                user.addRole(role.get());
            }
            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("Can't get order");
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        String query = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                User user = user = setUser(userId, name, surname, login, password, token).get();
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException e) {
            logger.error("Can't get all orders");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        String query = "UPDATE users SET name = ?, surname = ?, login = ?, password = ?"
                + " WHERE user_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("Can't update order");
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        String queryGetOrder = "SELECT * FROM orders WHERE user_id = ?;";
        List<Long> ordersId = new ArrayList<>();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(queryGetOrder)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");
                ordersId.add(orderId);
            }
        } catch (SQLException e) {
            logger.error("Can't get all orders");
        }
        for (Long orderId : ordersId) {
            orderDao.delete(orderId);
        }
        String queryRoles = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(queryRoles)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user");
        }
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user");
        }
    }

    @Override
    public Optional<User> login(String login, String password) {
        String query = "SELECT * FROM users WHERE login = ? AND password = ?;";
        Optional<User> user;
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                user = get(userId);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can't delete user");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?;";
        Optional<User> user;
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                user = get(userId);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can't delete user");
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getOrders(Long userId) {
        String query = "SELECT order_id FROM orders WHERE user_id = ?;";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");
                Optional<Order> order = orderDao.get(orderId);
                orders.add(order.get());
            }
            return orders;
        } catch (SQLException e) {
            logger.error("Can't get all orders");
        }
        return null;
    }

    @Override
    public Optional<User> addRole(Long userId, Long roleId) {
        String query = "INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add role");
        }
        return get(userId);
    }

    @Override
    public Optional<User> setUser(Long id, String name, String surname, String login,
                                  String password, String token) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setToken(token);
        return Optional.of(user);
    }
}