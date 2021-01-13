package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainingPlanServiceImplIT {

    @Test
    void checkExerciseexerciseNotFormat() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "1reroooo";
        repetitions = "3";
        series = "5";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid exercise", exception.getMessage());
    }

    @Test
    void checkExerciseexerciseNull() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = null;
        repetitions = null;
        series = null;
        recoveryTime = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("valori mancanti", exception.getMessage());
    }


    @Test
    void checkExerciseExcersNotLenght() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "pr";
        repetitions = "3";
        series = "6";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid exercise length", exception.getMessage());
    }

    @Test
    void checkExerciseExcersNotLenght25() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "praaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        repetitions = "3";
        series = "6";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid exercise length", exception.getMessage());
    }

    @Test
    void checkExerciseSeriesNotLenght() {


        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "prova";
        repetitions = "3";
        series = "888888";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid series length", exception.getMessage());
    }

    @Test
    void checkExerciseSeriesNotLenghtMin() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "prova";
        repetitions = "3";
        series = "";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid series length", exception.getMessage());
    }


    @Test
    void checkExerciseSeriesNotFormat() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "prova";
        repetitions = "3";
        series = "oo";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid series format", exception.getMessage());
    }


    @Test
    void checkExerciseRepetitionsNotFormat() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "ok";
        series = "15";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid repetitions format", exception.getMessage());
    }

    @Test
    void checkExerciseRepetitionsNotLenght() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "52525222";
        series = "15";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid repetitions length", exception.getMessage());
    }

    @Test
    void checkExerciseRepetitionsNotLenghtMin() {

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "";
        series = "15";
        recoveryTime = "5";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid repetitions length", exception.getMessage());
    }

    @Test
    void checkExerciseTimeNotFormat() {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "52";
        series = "15";
        recoveryTime = "5r";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid recoveryTime format", exception.getMessage());
    }

    @Test
    void checkExercisePass() {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);
        String exercise, repetitions, series, recoveryTime;
        exercise = "Addomaniali alti";
        repetitions = "52";
        series = "15";
        recoveryTime = "51";
        assertTrue(trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime));
    }

    @Test
    void checkExerciseTimeNotLenght() {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);
        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "52";
        series = "15";
        recoveryTime = "5000";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid recoveryTime length", exception.getMessage());
    }

    @Test
    void checkExerciseTimeNotLenghtMin() {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);
        String exercise, repetitions, series, recoveryTime;
        exercise = "Prova";
        repetitions = "52";
        series = "15";
        recoveryTime = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime);
        });
        assertEquals("invalid recoveryTime length", exception.getMessage());
    }

    @Test
    void createTrainingPlan() throws IOException, ExecutionException, InterruptedException {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);
        TrainingPlan tp_test = new TrainingPlan("Plank 30 10 50","ciao@ciao.it");
        AgendaDAO dao = new AgendaDAOImpl();
        Appointment x = new Appointment();
        dao.deleteappointment(x);
        assertEquals(true, trainingPlanService.createTrainingPlan(tp_test));
    }

    @AfterAll
    static void deleteTestTrainingPlan() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> list_tp = DBConnection.getConnection().collection("TrainingPlan").whereEqualTo("email","ciao@ciao.it").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_tp)
        {
            document.getReference().delete();
        }

    }


}
