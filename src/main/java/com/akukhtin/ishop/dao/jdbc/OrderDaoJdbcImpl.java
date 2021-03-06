package com.akukhtin.ishop.dao.jdbc;

import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.model.Order;
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
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static ItemDao itemDao;
    @Inject
    private static UserDao userDao;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Order> create(Order order) {
        String query = "INSERT INTO `orders` (`user_id`) VALUES (?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create order");
        }

        for (Item item : order.getItems()) {
            addItem(order.getId(), item.getId());
        }
        return Optional.of(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT order_id, user_id, item_id FROM orders INNER JOIN orders_items"
                + " USING (order_id) WHERE order_id = ?;";
        Order order = new Order();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");
                long userId = resultSet.getLong("user_id");
                long itemId = resultSet.getLong("item_id");
                order.setId(orderId);
                order.setUser(userDao.get(userId).get());
                Optional<Item> item = itemDao.get(itemId);
                order.getItems().add(item.get());
            }
            return Optional.of(order);
        } catch (SQLException e) {
            logger.error("Can't get order");
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");
                Order order = get(orderId).get();
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            logger.error("Can't get all orders");
        }
        return null;
    }

    @Override
    public Optional<Order> update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
            return Optional.of(order);
        } catch (SQLException e) {
            logger.error("Can't update order");
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        String queryItems = "DELETE FROM orders_items WHERE order_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(queryItems)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order");
        }
        String query = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order");
        }
    }

    @Override
    public Optional<Order> deleteByOrder(Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> addItem(Long orderId, Long itemId) {
        String query = "INSERT INTO `orders_items` (`order_id`, `item_id`) VALUES (?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add item");
        }
        return get(orderId);
    }
}
