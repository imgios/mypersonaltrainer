package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateNewTrainingPlanServlet", value = "/CreateNewTrainingPlanServlet")
public class CreateNewTrainingPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String exercise = request.getParameter("exercise");
        String repetitions = request.getParameter("repetitions");
        String series = request.getParameter("series");
        String recoveryTime = request.getParameter("recoveryTime");

        TrainingPlanService service = new TrainingPlanServiceImpl();

        //TrainingPlan tp = service.createTrainingPlan();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
