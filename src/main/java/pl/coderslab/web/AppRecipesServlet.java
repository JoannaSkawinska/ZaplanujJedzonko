package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipeString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/recipe/list/")
public class AppRecipesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

        /*AdminDao adminDao = new AdminDao();
        Admin loggedAdmin = adminDao.read(1);*/

        List<Recipe> listOfRecipes = RecipeDao.findAllRecipesOfAdmin(loggedAdmin.getId());
        List<RecipeString> listOfRecipesString = RecipeDao.recipeStringList(listOfRecipes);
        request.setAttribute("listOfRecipes", listOfRecipesString);
        request.getRequestDispatcher("/app_recipes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
