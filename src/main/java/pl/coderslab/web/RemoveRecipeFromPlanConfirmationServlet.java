package pl.coderslab.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.dao.PlanDao;

import java.io.IOException;

@WebServlet(name = "RemoveRecipeFromPlanConfirmationServlet", value = "/app/recipe/plan/delete")
public class RemoveRecipeFromPlanConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipePlanId = Integer.parseInt(request.getParameter("id"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        request.setAttribute("planId", planId);
        request.setAttribute("recipePlanId", recipePlanId);
        request.getRequestDispatcher("/delete_recipe_from_plan_confirmation.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));
        int planId = Integer.parseInt(request.getParameter("planId"));

        PlanDao.deleteRecipeFromPlan(recipePlanId);
        String redirect = "app/plan/details?id=" + planId;
        response.sendRedirect(redirect);
    }
}
