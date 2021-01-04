package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

class CreateTrainingPlanControllerTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    TrainingPlanServiceImpl trainingPlanService = Mockito.mock(TrainingPlanServiceImpl.class);

    @Test
    void doPost() {

        Mockito.when(request.getParameter("action")).thenReturn("addex");

        Mockito.when(request.getParameter("exercise")).thenReturn("exerciseNamw");
        Mockito.when(request.getParameter("repetitions")).thenReturn("5");
        Mockito.when(request.getParameter("series")).thenReturn("6");
        Mockito.when(request.getParameter("recoveryTime")).thenReturn("7");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        // doNothing().when(response).sendRedirect(anyString());

        Mockito.when(trainingPlanService.checkExercise("exercise", "5", "6", "7")).thenReturn(true);

        Mockito.when(request.getSession()
                .getAttribute("exercises")).thenReturn(null);
        doNothing().when(session).setAttribute(anyString(), any());

        //Mockito.when(request.getSession().setAttribute("exercises", "v")).thenReturn();



    }

    @Test
    void doGet() {
    }
}