package com.akukhtin.ishop;

import com.akukhtin.ishop.dao.BucketDao;
import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.RoleDao;
import com.akukhtin.ishop.dao.UserDao;

import com.akukhtin.ishop.dao.hibernate.ItemDaoHibernateImpl;
import com.akukhtin.ishop.dao.jdbc.BucketDaoJdbcImpl;
import com.akukhtin.ishop.dao.jdbc.OrderDaoJdbcImpl;
import com.akukhtin.ishop.dao.jdbc.RoleDaoJdbcImpl;
import com.akukhtin.ishop.dao.jdbc.UserDaoJdbcImpl;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.ItemService;
import com.akukhtin.ishop.service.OrderService;
import com.akukhtin.ishop.service.RoleService;
import com.akukhtin.ishop.service.UserService;
import com.akukhtin.ishop.service.impl.BucketServiceImpl;
import com.akukhtin.ishop.service.impl.ItemServiceImpl;
import com.akukhtin.ishop.service.impl.OrderServiceImpl;
import com.akukhtin.ishop.service.impl.RoleServiceImpl;
import com.akukhtin.ishop.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Factory {

    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/internetshop?"
                    + "user=root&password=qpalzmrfvQ1&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Can't establish connection to our DB", e);
        }
    }

    private static ItemDao itemDaoInstanse;
    private static BucketDao bucketDaoInstanse;
    private static OrderDao orderDaoInstanse;
    private static UserDao userDaoInstanse;
    private static RoleDao roleDaoInstanse;
    private static ItemService itemServiceInstanse;
    private static BucketService bucketServiceInstanse;
    private static OrderService orderServiceInstanse;
    private static UserService userServiceInstanse;
    private static RoleService roleServiceInstanse;

    public static ItemService getItemService() {
        if (itemServiceInstanse == null) {
            itemServiceInstanse = new ItemServiceImpl();
        }
        return itemServiceInstanse;
    }

    public static BucketService getBucketService() {
        if (bucketServiceInstanse == null) {
            bucketServiceInstanse = new BucketServiceImpl();
        }
        return bucketServiceInstanse;
    }

    public static OrderService getOrderService() {
        if (orderServiceInstanse == null) {
            orderServiceInstanse = new OrderServiceImpl();
        }
        return orderServiceInstanse;
    }

    public static UserService getUserService() {
        if (userServiceInstanse == null) {
            userServiceInstanse = new UserServiceImpl();
        }
        return userServiceInstanse;
    }

    public static RoleService getRoleService() {
        if (roleServiceInstanse == null) {
            roleServiceInstanse = new RoleServiceImpl();
        }
        return roleServiceInstanse;
    }

    public static ItemDao getItemDao() {
        if (itemDaoInstanse == null) {
            itemDaoInstanse = new ItemDaoHibernateImpl();
        }
        return itemDaoInstanse;
    }

    public static BucketDao getBucketDao() {
        if (bucketDaoInstanse == null) {
            bucketDaoInstanse = new BucketDaoJdbcImpl(connection);
        }
        return bucketDaoInstanse;
    }

    public static OrderDao getOrderDao() {
        if (orderDaoInstanse == null) {
            orderDaoInstanse = new OrderDaoJdbcImpl(connection);
        }
        return orderDaoInstanse;
    }

    public static UserDao getUserDao() {
        if (userDaoInstanse == null) {
            userDaoInstanse = new UserDaoJdbcImpl(connection);
        }
        return userDaoInstanse;
    }

    public static RoleDao getRoleDao() {
        if (roleDaoInstanse == null) {
            roleDaoInstanse = new RoleDaoJdbcImpl(connection);
        }
        return roleDaoInstanse;
    }
}
