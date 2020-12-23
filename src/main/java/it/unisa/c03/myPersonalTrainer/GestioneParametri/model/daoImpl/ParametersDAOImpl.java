package it.unisa.c03.myPersonalTrainer.GestioneParametri.model.daoImpl;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.dao.ParametersDAO;

import java.io.IOException;
import java.util.ArrayList;

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
}
