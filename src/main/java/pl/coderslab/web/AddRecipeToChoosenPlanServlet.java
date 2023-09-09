package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRecipeToChoosenPlanServlet", value = "/app/plan/recipe/add")
public class AddRecipeToChoosenPlanServlet extends HttpServlet {
    RecipeDao recipeDao = new RecipeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

        sess.setAttribute("authenticatedAdmin", loggedAdmin);
        List<Recipe> recipeOptions = recipeDao.findAllRecipes();
        List<DayName> dayOptions = DayNameDao.findAll();

        String planId = request.getParameter("planId");
        request.setAttribute("planId", planId);
        request.setAttribute("recipeOptions", recipeOptions);
        request.setAttribute("dayOptions", dayOptions);

        request.getRequestDispatcher("/app_add_recipe_to_plan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("choosePlan"));
        String mealName = request.getParameter("mealName");
        String recipeName = request.getParameter("recipe");
        int dayId = Integer.parseInt(request.getParameter("day"));
        int displayOrder = Integer.parseInt(request.getParameter("number"));

        PlanDao.addRecipeToPlan(planId, mealName, recipeName, dayId, displayOrder);

        HttpSession session = request.getSession();
        session.setAttribute("success", "Przepis dodany do planu");

        response.sendRedirect(request.getContextPath() + "/app/plan/list/details?id=" + planId);
    }
}
