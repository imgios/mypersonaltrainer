package it.unisa.c03.myPersonalTrainer.parameters.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.parameters.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * this is an implementation of ParametersDAO.
 */
public class ParametersDAOImpl implements ParametersDAO {

    /**
     * this method allows to insert user parameters into database.
     *
     * @param parameters the parameters to add into database
     */
    @Override
    public void insertParameters(Parameters parameters) {
        try {
            DBConnection.getConnection().collection(
                    "Parameters").add(parameters);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }


    /***
     *
     * @param email client mail to retrieve his parameters
     * @return a list of client parameters
     */
    @Override
    public ArrayList<Parameters> selectByMail(String email) {
        // Create a reference to the account collection
        CollectionReference parameters = null;
        try {
            parameters = DBConnection.getConnection().collection("Parameters");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a query against the collection.
        Query query = parameters.whereEqualTo("mailClient", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return document.get("email"));
        ArrayList<Parameters> list = new ArrayList<Parameters>();
        try {
            for (DocumentSnapshot document
                    : querySnapshot.get().getDocuments()) {
                Parameters params = new Parameters();
                params.setfatMass(Double.valueOf((Double)
                        document.get("fatMass")));
                params.setleanMass((Double)
                        document.get("leanMass"));
                params.setweight((Double)
                        document.get("weight"));
                params.setMailClient(
                        String.valueOf(document.get("mailClient")));
                params.setinsertionDate(
                        String.valueOf(document.get("insertionDate")));
                list.add(params);
            }
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return list;
    }
}
