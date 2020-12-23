package it.unisa.c03.myPersonalTrainer.GestioneParametri.model.daoImpl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.dao.ParametersDAO;

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
    public Parameters getByMail(String email) {
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

        Parameters params = new Parameters();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

                Object x = document.get("fatMass");
                System.out.println(x.getClass());
                params.setfatMass((( Float.parseFloat(String.valueOf(document.get("fatMass"))))));
                params.setleanMass((( Float.parseFloat(String.valueOf(document.get("leanMass"))))));
                params.setweight((( Float.parseFloat(String.valueOf(document.get("weight"))))));
                params.setMailClient(String.valueOf(document.get("mailClient")));
                params.setinsertionDate(String.valueOf(document.get("insertionDate")));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return params ;
    }
}
