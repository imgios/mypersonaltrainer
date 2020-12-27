package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;

public interface TrainingPlanDAO {

    public void saveTrainingPlan(TrainingPlan tp) throws IOException;


}
