package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;

public interface TrainingPlanService {


     boolean checkExercise(String exercise, String repetitions, String series, String recoveryTime)  throws IllegalArgumentException;

     boolean createTrainingPlan(TrainingPlan trainingPlan) throws IOException;


}
