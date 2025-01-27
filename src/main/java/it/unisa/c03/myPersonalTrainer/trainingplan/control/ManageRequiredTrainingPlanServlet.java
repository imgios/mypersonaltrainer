package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanService;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanServiceImpl;
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
        String action = request.getParameter("action");

        if (emailClientee == null) {
            response.sendRedirect("login.jsp");
        } else {
            int required = 0;
            RequiredTrainingPlan requireTest =
                    new RequiredTrainingPlan(emailClientee, required);
            //call the function to register a new account
            RequiredTrainingPlan newRequiredTP;
            RequiredTrainingPlanDAO requiredTrainingPlanDao =
                    new RequiredTrainingPlanDAOImpl();
            RequiredTrainingPlanService requiredTrainingPlanService =
                    new RequiredTrainingPlanServiceImpl(
                            requiredTrainingPlanDao);
            boolean checked = false;
            try {
                String mail = (String)
                        request.getSession().getAttribute("mailutil");

                checked = requiredTrainingPlanService.
                        searchAccountByEmail(emailClientee);
                if (!checked) {
                    //it doesn't exists, so we create it
                    requireTest.setRequired(1);
                    requiredTrainingPlanService.registerRequest(requireTest);

                    request.getSession().setAttribute("firstTPSuccess",
                            "Prima Scheda creata con successo");
                    response.sendRedirect("requestTrainingPlan.jsp");
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
                        request.getSession().setAttribute("alreadyRequired",
                                "Scheda già richiesta: "
                                        + "attenderne la creazione");
                        response.sendRedirect("requestTrainingPlan.jsp");
                    } else if (requireTest.getRequired() == 0) {
                        requireTest.setRequired(1);
                        requiredTrainingPlanService.
                                changeRequest(emailClientee, 1);
                        request.getSession().setAttribute("success",
                                "Scheda creata con successo");

                        response.sendRedirect("requestTrainingPlan.jsp");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
            boolean control = false;
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
