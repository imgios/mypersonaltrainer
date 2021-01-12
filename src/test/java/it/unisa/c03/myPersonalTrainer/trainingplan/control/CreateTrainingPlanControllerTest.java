package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService;
import it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

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
        request.getParameter("email");
        Mockito.when(session
                .getAttribute("exercises")).thenReturn(null);

        doNothing().when(session).setAttribute("noEx", "nontuttobene");

        Mockito.when(request.getParameter("email")).thenReturn("giampieroferrara@test.it");


        new CreateTrainingPlanController().doPost(request, response);

        System.out.println(request.getSession().getAttribute("noEx"));
    }

    @Test
    void doPostActionaddtpNotNull() throws IOException {

        Mockito.when(request.getParameter("action")).thenReturn("addtp");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("exercises")).thenReturn("someeserciziVirevi");

        //doNothing().when(session).setAttribute("exercises", "someeserciziplus");

        doNothing().when(session).setAttribute("success", "tuttobene");

        Mockito.when(request.getParameter("email")).thenReturn("giampieroferrara@test.it");

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

    /*
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
    */


    @AfterAll
    static void afterinserttp() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("TrainingPlan").whereEqualTo("email","giampieroferrara@test.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

}