package it.unisa.c03.myPersonalTrainer.trainingplan.bean;

import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl;

import java.io.IOException;
import java.time.LocalDate;

public class mainprova {

    public static void main(String[] args) throws IOException {
       /* String nomeScheda, exercise, repetitions, series, recoveryTime;
        final String exerciseName = "nome esercizio: ";
        final String nRepetitions = " ripetizioni";
        final String nseries = " serie";
        final String recoveryTimeSecond = " secondi di recupero";

        exercise = "nome esercizio";
        repetitions = "3";
        series = "5";
        recoveryTime = "5";

        String exercise1 = "addominali";
        String repetitions1 = "25";
        String series1 = "15";
        String recoveryTime1 = "58";

        String res = "";
        res += "\n" + exerciseName + "\t\t" + exercise + "\n" + repetitions + "\t\t\t" + nRepetitions + "\n" + series + "\t\t\t" + nseries + "\n" + recoveryTime + "\t\t\t" + recoveryTimeSecond;
        res += "\n" + exerciseName + "\t\t" + exercise1 + "\n" + repetitions1 + "\t\t\t" + nRepetitions + "\n" + series1 + "\t\t\t" + nseries + "\n" + recoveryTime1 + "\t\t\t" + recoveryTimeSecond;
*/
        //System.out.println(res);

        TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAOImpl();
       // LocalDate date = LocalDate.now();
      //  String dataaa = date.toString();

        //TrainingPlan tp = new TrainingPlan(res, dataaa, "clientemail@prova.io");
        TrainingPlan tp = new TrainingPlan("exerc prova", "provadat", "clientemail@prova.io");
        //System.out.println(tp.toString());

        trainingPlanDAO.saveTrainingPlan(tp);
    }
}
