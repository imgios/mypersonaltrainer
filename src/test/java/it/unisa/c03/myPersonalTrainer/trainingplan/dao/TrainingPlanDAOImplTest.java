package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class TrainingPlanDAOImplTest {

    TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();


    @BeforeAll
    static void populate() throws IOException {
    }

    @Test
    void testIns() throws IOException {
        assertTrue(trainingPlanDAO.insertTrainingPlan(new TrainingPlan("esercizi", "mailProva")));
    }

    @AfterAll
    static void spopola() throws InterruptedException, ExecutionException, IOException {
        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
        assertTrue(trainingPlanDAO.deleteTrainingPlan("mailProva"));
    }

    @Test
    void getTrainingPlansByEmail() throws InterruptedException, ExecutionException, IOException {
        assertNotNull(trainingPlanDAO.getTrainingPlansByEmail("provatest@prova.io"));
        assertSame(ArrayList.class, trainingPlanDAO.getTrainingPlansByEmail("provatest@prova.io").getClass());
        assertNotEquals(0, trainingPlanDAO.getTrainingPlansByEmail("provatest@prova.io").size());
    }




}