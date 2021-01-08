package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class CreateTrainingPlanControllerTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    TrainingPlanServiceImpl trainingPlanService = Mockito.mock(TrainingPlanServiceImpl.class);

    @Test
    void doPostexercisesNull() throws IOException {


        Mockito.when(request.getParameter("action")).thenReturn("addex");

        Mockito.when(request.getParameter("exercise")).thenReturn("exerciseNamw");
        Mockito.when(request.getParameter("repetitions")).thenReturn("5");
        Mockito.when(request.getParameter("series")).thenReturn("6");
        Mockito.when(request.getParameter("recoveryTime")).thenReturn("7");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(trainingPlanService.checkExercise("exercise", "5", "6", "7")).thenReturn(true);

        Mockito.when(session
                .getAttribute("exercises")).thenReturn(null);

        doNothing().when(session).setAttribute("success", "tuttobene");


        new CreateTrainingPlanController().doPost(request, response);
    }


    @Test
    void doPostexercisesNotNull() throws IOException {

        Mockito.when(request.getParameter("action")).thenReturn("addex");

        Mockito.when(request.getParameter("exercise")).thenReturn("exerciseNamw");
        Mockito.when(request.getParameter("repetitions")).thenReturn("5");
        Mockito.when(request.getParameter("series")).thenReturn("6");
        Mockito.when(request.getParameter("recoveryTime")).thenReturn("7");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);


        Mockito.when(trainingPlanService.checkExercise("exercise", "5", "6", "7")).thenReturn(true);

        Mockito.when(session
                .getAttribute("exercises")).thenReturn("someString");

        doNothing().when(session).setAttribute("success", "tuttobene");


        new CreateTrainingPlanController().doPost(request, response);
    }


    @Test
    void doPostActionaddtpNull() throws IOException {

        Mockito.when(request.getParameter("action")).thenReturn("addtp");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("exercises")).thenReturn(null);

        doNothing().when(session).setAttribute("noEx", "nontuttobene");



        new CreateTrainingPlanController().doPost(request, response);

        System.out.println(request.getSession().getAttribute("noEx"));
    }
    @Test
    void doPostActionaddtpNotNull() throws IOException {

        Mockito.when(request.getParameter("action")).thenReturn("addtp");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("exercises")).thenReturn("someesercizi");

        doNothing().when(session).setAttribute("exercises", "someeserciziplus");

        doNothing().when(session).setAttribute("success", "tuttobene");

        new CreateTrainingPlanController().doPost(request, response);

        System.out.println(request.getSession().getAttribute("noEx"));
    }



    @Test
    void doPostCatchExc() throws IOException {

        TrainingPlanDAO trainingPlanDAO = Mockito.mock(TrainingPlanDAO.class);
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        Mockito.when(request.getParameter("action")).thenReturn("addex");

        Mockito.when(request.getParameter("exercise")).thenReturn("exerciseName");
        Mockito.when(request.getParameter("repetitions")).thenReturn("5as");
        Mockito.when(request.getParameter("series")).thenReturn("6");
        Mockito.when(request.getParameter("recoveryTime")).thenReturn("7");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise("exerciseName", "5as", "6", "7");
        });
        assertEquals("invalid repetitions format", exception.getMessage());

        new CreateTrainingPlanController().doPost(request, response);
    }

    @Test
    void doGet() throws ServletException, IOException {


        TrainingPlanDAO trainingPlanDAO = Mockito.mock(TrainingPlanDAO.class);
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        Mockito.when(request.getParameter("action")).thenReturn("addex");

        Mockito.when(request.getParameter("exercise")).thenReturn("exerciseName");
        Mockito.when(request.getParameter("repetitions")).thenReturn("5as");
        Mockito.when(request.getParameter("series")).thenReturn("6");
        Mockito.when(request.getParameter("recoveryTime")).thenReturn("7");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise("exerciseName", "5as", "6", "7");
        });
        assertEquals("invalid repetitions format", exception.getMessage());

        new CreateTrainingPlanController().doGet(request, response);
    }
}