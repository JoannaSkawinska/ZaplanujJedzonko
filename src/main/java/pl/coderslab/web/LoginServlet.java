package pl.coderslab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AdminDao adminDao = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Admin authenticatedAdmin = adminDao.authenticate(email, password);

        if (authenticatedAdmin != null) {
            // logowanie udane
            req.getSession().setAttribute("authenticatedAdmin", authenticatedAdmin);
            resp.sendRedirect("/DashboarServlet");
        } else {
            // logowanie nieudane
            req.setAttribute("loginError", true);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
