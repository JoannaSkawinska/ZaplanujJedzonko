package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.PlanDao;

import java.io.IOException;

@WebServlet(name = "DeletePlanServlet", value = "/app/plan/delete")
public class DeletePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("planId", id);
        request.getRequestDispatcher("/app_plan_delete.jsp").forward(request, response);        ;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao.deletePlan(planId);
        response.sendRedirect("/app/plan/list");
    }
}
