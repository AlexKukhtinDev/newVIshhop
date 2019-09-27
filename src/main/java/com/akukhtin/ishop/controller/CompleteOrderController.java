package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.OrderService;
import com.akukhtin.ishop.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderController extends HttpServlet {

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        List<Item> items = new ArrayList<>(bucketService
                .getAllItems(bucketService.getByUser(userId).getId()));
        Order order = new Order(userId, items);
        orderService.create(order);
        userService.get(userId).getOrders().add(order);
        bucketService.clear(bucketService.getByUser(userId).getId());
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/completeOrder.jsp")
                .forward(req, resp);
    }
}
