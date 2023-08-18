package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin authenticatedAdmin = (Admin) session.getAttribute("authenticatedAdmin");

        int planId = Integer.parseInt(request.getParameter("planId"));
        Plan plan = PlanDao.read(planId);

        if (plan.getAdminId() != authenticatedAdmin.getId()) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("plan", plan);
        request.getRequestDispatcher("/app-edit-plan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin authenticatedAdmin = (Admin) session.getAttribute("authenticatedAdmin");

        String planIdParam = request.getParameter("planId");
        if (planIdParam == null || planIdParam.isEmpty()) {
            return;
        }

        int planId = Integer.parseInt(planIdParam);
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");

        Plan plan = PlanDao.read(planId);

        if (plan.getAdminId() != authenticatedAdmin.getId()) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

plan.setId(planId);
        plan.setName(planName);
        plan.setDescription(planDescription);

        PlanDao.updatePlan(plan);


        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }

}
