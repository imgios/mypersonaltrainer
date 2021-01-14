package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {

    TrainingPlanDAO tdao = new TrainingPlanDAOImpl();
    ArrayList<TrainingPlan> prova = (ArrayList<TrainingPlan>) tdao.getTrainingPlansByEmail("giampieroferrara@test.it");
    TrainingPlan t = prova.get(0);
    System.out.println(t.getExercises());

  }
}
