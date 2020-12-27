package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

public interface TrainingPlanService {

    public TrainingPlan createTrainingPlan();

    public boolean checkExercise(String exercise, String repetitions, String series, String recoveryTime)  throws IllegalArgumentException;



}
