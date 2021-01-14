package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TrainingPlanDAOImpl implements TrainingPlanDAO {

    /**
     * This method add a new TrainingPlan
     * @param tp
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean insertTrainingPlan(TrainingPlan tp)
            throws IOException {
        DBConnection.getConnection().collection("TrainingPlan").add(tp);
        return true;
    }

    /**
     * This method delete a TrainingPlan.
     * @param email
     * @return boolean
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean deleteTrainingPlan(
            String email)
            throws IOException, ExecutionException, InterruptedException {

        Firestore connection =
                DBConnection.getConnection();
        List<QueryDocumentSnapshot> lqds =
                connection.collection("TrainingPlan").
                        whereEqualTo("email", email).
                        get().get().getDocuments();
        for (QueryDocumentSnapshot document : lqds) {
            document.getReference().delete();
        }
        return true;
    }

    /**
     * This method extracts training plans into database.
     * @param email of the customer
     * @return List of customer's training plan.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Collection<TrainingPlan> getTrainingPlansByEmail(String email)
            throws IOException, ExecutionException, InterruptedException {

        Firestore connection =
                DBConnection.getConnection();

        List<QueryDocumentSnapshot> l =
                connection.collection("TrainingPlan").
                        whereEqualTo("email", email).
                        get().get().getDocuments();

        List<TrainingPlan> trainingPlanBean = null;

        trainingPlanBean = l.stream().
                map(queryDocumentSnapshot ->
                        queryDocumentSnapshot.toObject(TrainingPlan.class))
                .collect(Collectors.toList());
        return trainingPlanBean;
    }

}
