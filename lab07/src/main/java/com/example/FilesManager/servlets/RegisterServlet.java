package com.example.FilesManager.servlets;

import com.example.FilesManager.models.User;
import com.example.FilesManager.services.CookieUtil;
import com.example.FilesManager.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (DbService.USER_SERVICE.getUserByCookies(req.getCookies()) != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (login == null
                || DbService.USER_SERVICE.getUser(login) != null
                || email == null || password == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        }

        User user = new User(login,  password, email);
;
        DbService.USER_SERVICE.addUser(user);
        CookieUtil.addCookie(resp, "login", login);
        CookieUtil.addCookie(resp, "password", password);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
