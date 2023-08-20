package pl.coderslab.web;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlanServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        /*AdminDao adminDao = new AdminDao();
        Admin loggedAdmin = adminDao.read(1);*/

        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

        sess.setAttribute("authenticatedAdmin", loggedAdmin);
        List<Plan> planOptions = PlanDao.findAllPlansOfAdmin(loggedAdmin.getId());
        List<Recipe> recipeOptions = RecipeDao.findAllRecipesOfAdmin(loggedAdmin.getId());
        List<DayName> dayOptions = DayNameDao.findAll();

        request.setAttribute("planOptions", planOptions);
        request.setAttribute("recipeOptions", recipeOptions);
        request.setAttribute("dayOptions", dayOptions);

        request.getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request, response);
    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

List<Plan> planOptions = PlanDao.findAllPlansOfAdmin(loggedAdmin.getId());
        List<Recipe> recipeOptions = RecipeDao.findAllRecipesOfAdmin(loggedAdmin.getId());
        List<DayName> dayOptions = DayNameDao.findAll();

        request.setAttribute("planOptions", PlanDao.findAllPlansOfAdmin(loggedAdmin.getId()));
        request.setAttribute("recipeOptions", RecipeDao.findAllRecipesOfAdmin(loggedAdmin.getId()));
        request.setAttribute("dayOptions", DayNameDao.findAll());

        request.getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request, response);
    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("choosePlan"));
        String mealName = request.getParameter("mealName");
        String recipeName = request.getParameter("recipe");
        int dayId = Integer.parseInt(request.getParameter("day")); //
        int displayOrder = Integer.parseInt(request.getParameter("number"));

        PlanDao.addRecipeToPlan(planId, mealName, recipeName, dayId, displayOrder);

        HttpSession session = request.getSession();
        session.setAttribute("success", "Przepis dodany do planu");

        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }
}