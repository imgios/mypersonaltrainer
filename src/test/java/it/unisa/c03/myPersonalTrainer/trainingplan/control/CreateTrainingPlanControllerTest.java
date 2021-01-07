package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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

        Mockito.when(request.getSession()
                .getAttribute("exercises")).thenReturn(null);

        new CreateTrainingPlanController().doPost(request, response);

        assertNotEquals("ciao", request.getSession().getAttribute("exercises"));
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

       Mockito.when(request.getSession()
                .getAttribute("exercises")).thenReturn("someString");

        new CreateTrainingPlanController().doPost(request, response);

        assertNotEquals(null, request.getSession().getAttribute("exercises"));
        System.out.println(session.getAttribute("exercises"));

    }


    @Test
    void doPostActionaddtpNull() throws IOException {

        Mockito.when(request.getParameter("action")).thenReturn("other");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(request.getSession()
                .getAttribute("exercises")).thenReturn("someString");


        new CreateTrainingPlanController().doPost(request, response);

        System.out.println(request.getSession().getAttribute("noEx"));
    }


    @Test
    void doGet() {
    }
}