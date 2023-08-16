package pl.coderslab.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import java.io.IOException;

@WebServlet(name = "EditRecipeServlet", value = "/app/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("id"));
        Recipe recipeToEdit = RecipeDao.read(recipeId);
        String oldRecipeName = recipeToEdit.getName();
        String oldRecipeDescription = recipeToEdit.getDescription();
        String oldRecipePreparationTime = String.valueOf(recipeToEdit.getPreparationTime());
        String oldRecipePreparation = recipeToEdit.getPreparation();
        String oldRecipeIngredients = recipeToEdit.getIngredients();

        request.setAttribute("recipeId", recipeId);
        request.setAttribute("oldRecipeName", oldRecipeName);
        request.setAttribute("oldRecipeDescription", oldRecipeDescription);
        request.setAttribute("oldRecipePreparationTime", oldRecipePreparationTime);
        request.setAttribute("oldRecipePreparation", oldRecipePreparation);
        request.setAttribute("oldRecipeIngredients", oldRecipeIngredients);
        request.getRequestDispatcher("/app_recipe_edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
