package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.io.IOException;

@WebServlet(name = "RecipeUpdateServlet", value = "/app/recipe/update")
public class RecipeUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String oldRecipeName = request.getParameter("oldRecipeName");
        String oldRecipeDescription = request.getParameter("oldRecipeDescription");
        int oldRecipePreparationTime = Integer.parseInt(request.getParameter("oldRecipePreparationTime"));
        String oldRecipePreparation = request.getParameter("oldRecipePreparation");
        String oldRecipeIngredients = request.getParameter("oldRecipeIngredients");

        String newRecipeName = request.getParameter("newRecipeName");
        String newRecipeDescription = request.getParameter("newRecipeDescription");
        String newRecipePreparationTimeString = request.getParameter("newRecipePreparationTime");
        if (newRecipePreparationTimeString.equals("")) {
            newRecipePreparationTimeString = "0";
        }
        int newRecipePreparationTime = Integer.parseInt(newRecipePreparationTimeString);
        String newRecipePreparation = request.getParameter("newRecipePreparation");
        String newRecipeIngredients = request.getParameter("newRecipeIngredients");

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(recipeId);
        if (newRecipeName.equals("")) {
            updatedRecipe.setName(oldRecipeName);
        } else {
            updatedRecipe.setName(newRecipeName);
        }
        if (newRecipeDescription.equals("")) {
            updatedRecipe.setDescription(oldRecipeDescription);
        } else {
            updatedRecipe.setDescription(newRecipeDescription);
        }
        if (newRecipePreparationTime == 0) {
            updatedRecipe.setPreparationTime(oldRecipePreparationTime);
        } else {
            updatedRecipe.setPreparationTime(newRecipePreparationTime);
        }
        if (newRecipePreparation.equals("")) {
            updatedRecipe.setPreparation(oldRecipePreparation);
        } else {
            updatedRecipe.setPreparation(newRecipePreparation);
        }
        if (newRecipeIngredients.equals("")) {
            updatedRecipe.setIngredients(oldRecipeIngredients);
        } else {
            updatedRecipe.setIngredients(newRecipeIngredients);
        }
        updatedRecipe.setUpdated(DbUtil.getCurrentDateTime());
        RecipeDao.updateRecipe(updatedRecipe);
        response.sendRedirect("/app/recipe/list/");
    }
}
