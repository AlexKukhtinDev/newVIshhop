package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.model.Bucket;
import com.akukhtin.ishop.model.Role;
import com.akukhtin.ishop.model.User;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.UserService;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        if (req.getParameter("psw").equals(req.getParameter("psw-repeat"))) {
            newUser.setPassword(req.getParameter("psw-repeat"));
        } else {
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        newUser.setLogin(req.getParameter("login"));
        newUser.setName(req.getParameter("user_name"));
        newUser.setSurname(req.getParameter("user_surname"));
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        newUser.addRole(role);
        Optional<User> user = userService.create(newUser);
        Bucket newBucket = new Bucket(newUser);
        bucketService.create(newBucket);

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.get().getId());

        Cookie cookie = new Cookie("Mate", user.get().getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
