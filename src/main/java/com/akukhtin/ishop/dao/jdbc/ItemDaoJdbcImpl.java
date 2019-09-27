package com.akukhtin.ishop.dao.jdbc;

import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_NAME = "ishop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO " + DB_NAME + ".items (name, price, model) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getModel());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Item newItem = null;
            if (resultSet.next()) {
                newItem = get(resultSet.getLong(1));
            }
            return newItem;
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement statement = null;
        String query = "SELECT * FROM " + DB_NAME + ".items where item_id=" + id + ";";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId, name, price);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id = " + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items SET name= '?', price = '?', model= '?' "
                + "WHERE item_id = '?';";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can't add new item", e);
        }
        return null;
    }

    @Override
    public Item delete(Long id) {
        Statement statement = null;
        String query = "DELETE FROM " + DB_NAME + ".items where item_id=" + id + ";";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return null;
        } catch (SQLException e) {
            logger.warn("Can't delete item by id = " + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item deleteByItem(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
