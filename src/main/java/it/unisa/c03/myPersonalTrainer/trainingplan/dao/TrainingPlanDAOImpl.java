package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;

public class TrainingPlanDAOImpl implements TrainingPlanDAO{

    public void saveTrainingPlan(TrainingPlan tp) throws IOException {
        DBConnection.getConnection().collection("TrainingPlan").add(tp);
    }

}
