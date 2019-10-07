package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.ItemService;
import com.akukhtin.ishop.service.UserService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBucketToItemController extends HttpServlet {

    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String itemId = req.getParameter("item_id");
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        bucketService.addItem(bucketService.getByUser(userId).get().getId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
