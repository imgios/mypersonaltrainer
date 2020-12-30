package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        System.out.println(tp);
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

}
