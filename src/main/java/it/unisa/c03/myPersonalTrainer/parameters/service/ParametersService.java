package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ParametersService {
    Parameters createParameters(String weight, String leanMass,
                                String fatMass)
            throws NumberFormatException, IllegalArgumentException, IOException;

    ArrayList<Parameters> getByMail(String email) throws InterruptedException, ExecutionException, IOException;
}
