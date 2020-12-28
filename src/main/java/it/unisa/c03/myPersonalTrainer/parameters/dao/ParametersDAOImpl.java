package it.unisa.c03.myPersonalTrainer.parameters.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
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
    public boolean insertParameters(Parameters parameters) throws IOException {
        DBConnection.getConnection().collection(
                "Parameters").add(parameters);
        return true;
    }
}
