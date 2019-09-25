package com.akukhtin.ishop.dao.impl;

import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.Storage;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.Order;

import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find number with id " + id));
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
                return order;
            }
        }
        throw new NoSuchElementException("Can't find item" + order.getId());
    }

    @Override
    public Order delete(Long id) {
        Order order = get(id);
        Storage.orders.removeIf(s -> s.getId().equals(id));
        return order;
    }

    @Override
    public Order deleteByOrder(Order order) {
        Storage.orders.removeIf(s -> s.equals(order));
        return order;
    }
}
