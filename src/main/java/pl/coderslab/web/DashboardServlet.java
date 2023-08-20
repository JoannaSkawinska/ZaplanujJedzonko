package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
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
        AdminDao adminDao = new AdminDao();
        Admin loggedAdmin = adminDao.read(1);
        HttpSession sess = request.getSession();
        /*Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");*/
        sess.setAttribute("authenticatedAdmin", loggedAdmin);
        int noOfPlans = PlanDao.numberOfPlansOfAdmin(loggedAdmin);
        request.setAttribute("noOfPlans", noOfPlans);

        int noOfRecipes = RecipeDao.numberOfRecipesOfAdmin(loggedAdmin);
        request.setAttribute("noOfRecipes", noOfRecipes);

        List<PlanString> lastPlanOfLoggedAdmin = PlanDao.getLastPlanOfAdmin(loggedAdmin);
        request.setAttribute("lastPlanOfLoggedAdmin", lastPlanOfLoggedAdmin);

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
