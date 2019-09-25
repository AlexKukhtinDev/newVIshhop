package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Order;

import java.util.List;

public interface OrderDao {

    Order create(Order item);

    Order get(Long id);

    List<Order> getAll();

    Order update(Order item);

    Order delete(Long id);

    Order deleteByOrder(Order order);
}
