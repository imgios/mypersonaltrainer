package it.unisa.c03.myPersonalTrainer.parameters.dao;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ParametersDAO {

    void insertParameters(Parameters p) throws IOException;

    ArrayList<Parameters> selectByMail(String email)
            throws IOException, ExecutionException, InterruptedException;
}
