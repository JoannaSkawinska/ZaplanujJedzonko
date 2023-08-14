package pl.coderslab.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.model.Admin;

import java.io.IOException;

@WebServlet(name = "AppSchedulesServlet", value = "/AppSchedulesServlet")
public class AppSchedulesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

        request.getRequestDispatcher("/app-schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
