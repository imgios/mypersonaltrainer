package it.unisa.c03.myPersonalTrainer.GestioneParametri.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ParametersDAOImpl implements ParametersDAO {

    @Override
    public void insertParameters(Parameters p) {
        try {
            DBConnection.getConnection().collection("Parameters").add(p);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }


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
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Parameters params = new Parameters();

                params.setfatMass(Double.valueOf((Double) document.get("fatMass")));
                params.setleanMass((Double) document.get("leanMass"));
                params.setweight((Double) document.get("weight"));
                params.setMailClient(String.valueOf(document.get("mailClient")));
                params.setinsertionDate(String.valueOf(document.get("insertionDate")));
                list.add(params) ;
            }
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
