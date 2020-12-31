package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import com.google.api.core.ApiFuture;
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
     * This method extracts trainingplans into database.
     * @param email of the customer
     * @return List of Training Plans
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Collection<TrainingPlan> getTrainingPlansByEmail(String email)
            throws IOException, ExecutionException, InterruptedException{

        CollectionReference db = null;

        try {
            db = DBConnection.getConnection().collection("TrainingPlan");
        }catch (IOException e){
            e.printStackTrace();
        }

        Query query = db.whereEqualTo("email", email);

        ApiFuture<QuerySnapshot> trainingPlans = db.get();

        List<TrainingPlan> trainingPlanList = null;

        try{
            trainingPlanList = trainingPlans.get()
                    .getDocuments()
                    .stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(TrainingPlan.class))
                    .collect(Collectors.toList());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        return trainingPlanList;

    }

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
