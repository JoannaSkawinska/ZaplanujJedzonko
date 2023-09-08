package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanString;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "PlanDetailServlet", value = "/app/plan/details")
public class PlanDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        Admin loggedAdmin = (Admin) sess.getAttribute("authenticatedAdmin");
        int planId = Integer.parseInt(request.getParameter("id"));
        List<PlanString> planDetails = PlanDao.getListOfPlansOfAdmin(loggedAdmin, planId);
        request.setAttribute("planDetails", planDetails);
        request.setAttribute("planId", planId);

        request.getRequestDispatcher("/app_plan_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
