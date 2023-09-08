package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;



import java.io.IOException;

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {

    private final RecipeDao recipeDao = new RecipeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");
        request.setAttribute("admin", loggedAdmin);
        request.getRequestDispatcher("/app-add-recipe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
       int adminId = Integer.parseInt(request.getParameter("admin_id"));
        String created = request.getParameter("created");

        Recipe newRecipe = new Recipe();
        newRecipe.setName(name);
        newRecipe.setDescription(description);
        newRecipe.setPreparationTime(preparationTime);
        newRecipe.setPreparation(preparation);
        newRecipe.setIngredients(ingredients);
        newRecipe.setAdminId(adminId);
        newRecipe.setCreated(created);


        recipeDao.createNewRecipe(newRecipe);


        response.sendRedirect(request.getContextPath() + "/app/recipe/list/");
    }
}
