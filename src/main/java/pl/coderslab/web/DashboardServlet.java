package pl.coderslab.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.PlanString;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");
        int noOfPlans = PlanDao.numberOfRecipesOfAdmin(loggedAdmin);
        request.setAttribute("noOfPlans", noOfPlans);

        int noOfRecipes = RecipeDao.numberOfPlansOfAdmin(loggedAdmin);
        request.setAttribute("noOfRecipes", noOfRecipes);

        List<PlanString> lastPlanOfLoggedAdmin = PlanDao.lastPlanOfAdmin(loggedAdmin);
        request.setAttribute("lastPlanOfLoggedAdmin", lastPlanOfLoggedAdmin);

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
