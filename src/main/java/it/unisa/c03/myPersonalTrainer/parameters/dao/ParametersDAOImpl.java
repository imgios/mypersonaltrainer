package it.unisa.c03.myPersonalTrainer.parameters.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

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
    public boolean insertParameters(
            Parameters parameters) throws IOException {
        DBConnection.getConnection().collection(
                "Parameters").add(parameters);
        return true;
    }


    /***
     *
     * @param email client mail to retrieve his parameters
     * @return a list of client parameters
     */
    @Override
    public ArrayList<Parameters> selectByMail(
            String email) throws
            InterruptedException, IOException, ExecutionException {
        // Create a reference to the it.unisa.c03.myPersonalTrainer.it.unisa.c03.myPersonalTrainer.account collection
        CollectionReference parameters = null;
        parameters = DBConnection.getConnection().collection("Parameters");

        // Create a query against the collection.
        Query query = parameters.whereEqualTo("mailClient", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return document.get("email"));
        ArrayList<Parameters> list = new ArrayList<Parameters>();
        for (DocumentSnapshot document
                : querySnapshot.get().getDocuments()) {
            Parameters params = new Parameters();
            params.setfatMass((Double)
                    document.get("fatMass"));
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
    }
}
