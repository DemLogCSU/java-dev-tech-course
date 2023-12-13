package site.dlsky;

import site.dlsky.models.User;
import site.dlsky.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserRepository.USER_REPOSITORY.getUserByCookies(req.getCookies());
        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            return;
        }

        User user = UserRepository.USER_REPOSITORY.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            resp.sendRedirect("/login");
            return;
        }

        UserRepository.USER_REPOSITORY.addUserBySession(MyCookie.getValue(req.getCookies(), "JSESSIONID"), user);
        resp.sendRedirect("/");
    }
}
