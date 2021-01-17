package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl;

/**
 * servlet to manage requestedTrainingPlan.
 */
@WebServlet(name = "ManageRequiredTrainingPlanServlet",
        value = "/ManageRequiredTrainingPlanServlet")

public class ManageRequiredTrainingPlanServlet extends HttpServlet {

    /**
     * method do post.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String error = (String) request.getSession().getAttribute("error");
        String done = (String) request.getSession().getAttribute("done");

        String emailClientee = (String)
                request.getSession().getAttribute("clienteMail");

        if (emailClientee == null) {
            response.sendRedirect("login.jsp");
        } else {
            int required = 0;
            RequiredTrainingPlan requireTest =
                    new RequiredTrainingPlan(emailClientee, required);

            //call the function to register a new account
            RequiredTrainingPlanDAO requiredTrainingPlanDao =
                    new RequiredTrainingPlanDAOImpl();
            RequiredTrainingPlanService requiredTrainingPlanService =
                    new RequiredTrainingPlanServiceImpl(
                            requiredTrainingPlanDao);
            boolean checked = false;

            try {
                checked = requiredTrainingPlanService.
                        searchAccountByEmail(emailClientee);

                System.out.println("Vediamo checked: " + checked);
                if (!checked) {
                    //it doesn't exists, so we create it
                    requireTest.setRequired(1);
                    requiredTrainingPlanService.registerRequest(requireTest);

                    response.sendRedirect("customerDashboard.jsp");
                } else {
                    try {
                        requireTest = requiredTrainingPlanService
                                .getAccountByEmail(emailClientee);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //it exists, so we verify if the training plan has
                    //  been requested or not
                    if (requireTest.getRequired() == 1) {
                        response.sendRedirect("customerDashboard.jsp");
                    } else if (requireTest.getRequired() == 0) {
                        requireTest.setRequired(1);
                        requiredTrainingPlanService.
                                changeRequest(emailClientee, 1);
                        response.sendRedirect("customerDashboard.jsp");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            boolean control = false;
        }
    }
        /**
         *
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */
        public void doGet(HttpServletRequest request,
                HttpServletResponse response)
            throws ServletException, IOException {
            doPost(request, response);
        }

}
