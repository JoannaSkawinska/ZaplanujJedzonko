package pl.coderslab.web;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

        @WebServlet("/app/recipe/plan/add")
        public class AddRecipeToPlanServlet extends HttpServlet {
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession sess = request.getSession();
                Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");

                request.setAttribute("plans", PlanDao.findAllPlans());
                request.setAttribute("recipes", RecipeDao.findAllRecipesOfAdmin(loggedAdmin.getId()));
                request.setAttribute("days", DayNameDao.findAll());
                 request.getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request, response);
            }

            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                int planId = Integer.parseInt(request.getParameter("choosePlan"));
                String mealName = request.getParameter("mealName");
                String recipeName = request.getParameter("recipeName");
                String dayName = request.getParameter("dayName");
                int displayOrder = Integer.parseInt(request.getParameter("number"));


                PlanDao.addRecipeToPlan(planId, mealName, recipeName, dayName, displayOrder);

                HttpSession session = request.getSession();
                session.setAttribute("success", "Przepis dodany do planu");

                response.sendRedirect(request.getContextPath() + "/app/recipe/plan/add");

            }
        }

