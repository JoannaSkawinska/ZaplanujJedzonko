package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.RecipeDao;

import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value = "/app/recipe/delete")
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("recipeId", recipeId);
        request.getRequestDispatcher("/app_recipe_delete.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao.deleteRecipe(recipeId);
        response.sendRedirect("/app/recipe/list/");
    }
}
