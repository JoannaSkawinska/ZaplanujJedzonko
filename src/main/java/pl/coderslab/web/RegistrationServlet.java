package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");


        if (!password.equals(repassword)) {

            req.setAttribute("error", "Hasła nie są takie same");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            return;
        }


        Admin admin = new Admin();
        admin.setFirstName(name);
        admin.setLastName(surname);
        admin.setEmail(email);
        admin.setPassword(password);


        pl.coderslab.dao.AdminDao.createNewAdmin(admin);


        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}