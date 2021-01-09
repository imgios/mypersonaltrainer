package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TrainingPlanServiceImplTest {
    TrainingPlanDAO trainingPlanDAO = Mockito.mock(TrainingPlanDAO.class);
    TrainingPlanService trainingPlanService = new TrainingPlanServiceImpl(trainingPlanDAO);

    @Test
    void checkExerciseexerciseNotFormat() {

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

        String exercise, repetitions, series, recoveryTime;
        exercise = "Addomaniali alti";
        repetitions = "52";
        series = "15";
        recoveryTime = "51";
        assertTrue(trainingPlanService.checkExercise(exercise, repetitions, series, recoveryTime));
    }


    @Test
    void checkExerciseTimeNotLenght() {

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
    void createTrainingPlan() throws IOException {
        Mockito.when(trainingPlanDAO.insertTrainingPlan(any())).thenReturn(true);
        assertEquals(true, trainingPlanService.createTrainingPlan(any()));
    }

    @Test
    void createTrainingPlanFals() throws IOException {
        Mockito.when(trainingPlanDAO.insertTrainingPlan(any())).thenReturn(false);
        assertEquals(false, trainingPlanService.createTrainingPlan(any()));
    }
}