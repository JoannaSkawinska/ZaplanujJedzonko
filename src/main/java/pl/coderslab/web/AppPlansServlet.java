package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppPlansServlet", value = "/app/plan/list")
public class AppPlansServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");
        List<Plan> planList = PlanDao.findAllPlansOfAdmin(loggedAdmin.getId());
        request.setAttribute("planList", planList);
        request.getRequestDispatcher("/app_plans.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
