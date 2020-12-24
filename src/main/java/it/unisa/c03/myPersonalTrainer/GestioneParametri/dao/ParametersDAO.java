package it.unisa.c03.myPersonalTrainer.GestioneParametri.dao;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;

import java.util.ArrayList;

public interface ParametersDAO {
    void insertParameters(Parameters p);

    ArrayList<Parameters> selectByMail(String email);

}
