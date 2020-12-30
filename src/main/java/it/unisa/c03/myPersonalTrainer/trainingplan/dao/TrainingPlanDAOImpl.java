package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainingPlanDAOImpl implements TrainingPlanDAO {
    @Override
    public boolean insertTrainingPlan(TrainingPlan tp) throws IOException {
        System.out.println(tp);
        DBConnection.getConnection().collection("TrainingPlan").add(tp);
        return true;
    }


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
