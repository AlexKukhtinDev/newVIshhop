package com.akukhtin.ishop.service.impl;

import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.lib.Service;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Optional<Order> completeOrder(List<Item> items, Long userId) {
        Order order = new Order();
        order.setItems(items);
        order.setUser(userDao.get(userId).get());
        orderDao.create(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Item> getAllItems(Long orderId) {
        Optional<Order> order = get(orderId);
        return order.get().getItems();
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Optional<Order> deleteByOrder(Order order) {
        return orderDao.deleteByOrder(order);
    }
}
