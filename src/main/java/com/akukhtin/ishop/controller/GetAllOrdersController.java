package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.service.UserService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOrdersController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        List<Order> orders = userService.getOrders(userId).get();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/allOrders.jsp").forward(req, resp);
    }
}
