package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface TrainingPlanDAO {

    boolean insertTrainingPlan(TrainingPlan tp)
            throws IOException;

    boolean deleteTrainingPlan(
            String email)
            throws IOException, ExecutionException, InterruptedException;


}
