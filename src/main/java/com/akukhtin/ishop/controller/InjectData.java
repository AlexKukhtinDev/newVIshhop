package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Bucket;
import com.akukhtin.ishop.model.Role;
import com.akukhtin.ishop.model.User;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectData extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User("Alex");
        user.setSurname("Kukhtin");
        user.setLogin("Cooper");
        user.setPassword("11");
        user.addRole(Role.of("USER"));
        Bucket newBucketUser = new Bucket(user);
        bucketService.create(newBucketUser);
        user.setBucket(newBucketUser);
        userService.create(user);

        User admin = new User("SuperStar");
        admin.setSurname("Admin");
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
