package pl.coderslab.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import java.io.IOException;

@WebServlet(name = "RecipeDetailsServlet", value = "/app/recipe/details")
public class RecipeDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer recipeId = Integer.parseInt(request.getParameter("id"));
        Recipe recipeToShow = RecipeDao.read(recipeId);
        String ingredients = recipeToShow.getIngredients();
        request.setAttribute("recipe", recipeToShow);
        request.setAttribute("ingredients", ingredients);
        request.getRequestDispatcher("/app_recipe_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
