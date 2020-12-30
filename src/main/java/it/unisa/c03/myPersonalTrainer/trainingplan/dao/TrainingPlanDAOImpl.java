package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;

public class TrainingPlanDAOImpl implements TrainingPlanDAO {
    @Override
    public boolean saveTrainingPlan(TrainingPlan tp) throws IOException {
        System.out.println(tp);
        DBConnection.getConnection().collection("TrainingPlan").add(tp);
        return true;
    }
}
