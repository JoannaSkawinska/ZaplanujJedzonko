package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {

    private final RecipeDao recipeDao = new RecipeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("app-add-recipe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        Recipe newRecipe = new Recipe();
        newRecipe.setName(name);
        newRecipe.setDescription(description);
        newRecipe.setPreparationTime(preparationTime);
        newRecipe.setPreparation(preparation);
        newRecipe.setIngredients(ingredients);


        recipeDao.createNewRecipe(newRecipe);


        response.sendRedirect(request.getContextPath() + "/app/recipes");
    }
}
