package it.unisa.c03.myPersonalTrainer.GestioneParametri.model.dao;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.bean.Parameters;

import java.util.ArrayList;

public interface ParametersDAO {
    void insertParameters(Parameters p);

    Parameters getByMail(String email);

}
