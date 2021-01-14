package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateNewTrainingPlanServlet",
        value = "/createTP-controller")
public class CreateTrainingPlanServlet extends
        HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        TrainingPlanDAO trainingPlanDAO =
                new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService =
                new TrainingPlanServiceImpl(trainingPlanDAO);

        try {
            String action = request.getParameter("action");
            if (action.equals("addex")) {

                String exercise;
                String repetitions;
                String series;
                String recoveryTime;
                final String exerciseName = "nome esercizio: ";
                final String nRepetitions = " ripetizioni";
                final String nseries = " serie";
                final String recoveryTimeSecond = " secondi di recupero";

                exercise = request.getParameter("exercise");
                repetitions = request.getParameter("repetitions");
                series = request.getParameter("series");
                recoveryTime = request.getParameter("recoveryTime");

                trainingPlanService.checkExercise(exercise, repetitions,
                        series, recoveryTime);


                String exercises = (String) request.getSession()
                        .getAttribute("exercises");
                if (exercises == null) {
                    String firstEx = "\n" + exerciseName
                            + "\t" + exercise
                            + "\n" + repetitions
                            + "\t\t\t\t" + nRepetitions
                            + "\n" + series
                            + "\t\t\t\t" + nseries
                            + "\n" + recoveryTime
                            + "\t\t\t\t" + recoveryTimeSecond;
                    request.getSession().setAttribute("exercises", firstEx);
                } else {
                    String notFirstEx = "\n\n" + exerciseName
                            + "\t" + exercise
                            + "\n" + repetitions
                            + "\t\t\t\t" + nRepetitions
                            + "\n" + series
                            + "\t\t\t\t" + nseries
                            + "\n" + recoveryTime
                            + "\t\t\t\t" + recoveryTimeSecond;
                    exercises += notFirstEx;
                    request.getSession().setAttribute("exercises", exercises);
                }
                request.getSession()
                        .setAttribute("success", "Inserimento effettuato, "
                                + "puoi continuare ad inserire esercizi "
                                + "o cliccare su Crea Scheda");

                response.sendRedirect("createTrainingPlan.jsp");

            } else if (action.equals("addtp")) {


                String mail = (String) request.getSession().getAttribute("mailutil");

                String exercises = (String) request.getSession()
                        .getAttribute("exercises");
                if (exercises == null) {
                    request.getSession().setAttribute("noEx",
                            "Inserisci almeno un esercizio per creare una scheda!");
                    response.sendRedirect("createTrainingPlan.jsp");
                } else {


                    TrainingPlan trainingPlan =
                            new TrainingPlan(exercises, mail);
                    trainingPlanService.createTrainingPlan(trainingPlan);
                    request.getSession().removeAttribute("exercises");
                    request.getSession().setAttribute("success",
                            "Scheda creata con successo");
                    response.sendRedirect("createTrainingPlan.jsp");
                }
            }
        } catch (IllegalArgumentException e) {

            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("createTrainingPlan.jsp");
        }
    }

    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
