package it.unisa.c03.myPersonalTrainer.parameters.dao;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.util.ArrayList;

public interface ParametersDAO {

    void insertParameters(Parameters p);

    ArrayList<Parameters> selectByMail(String email);
}
