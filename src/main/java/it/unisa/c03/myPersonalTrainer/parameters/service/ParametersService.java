package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ParametersService {
    /**
     * this methods check if the parameters are acceptable; then enable to insert them into database
     *
     * @param weight
     * @param leanMass
     * @param fatMass
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     */
    Parameters createParameters(String weight, String leanMass,
                                String fatMass)
            throws IllegalArgumentException, IOException;

    ArrayList<Parameters> getByMail(String email)
            throws InterruptedException, ExecutionException, IOException;

}
