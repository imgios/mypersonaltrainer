package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ParametersService {

    /**
     * create parameters method
     * this methods check if the parameters are acceptable;
     * then able to be insert into database.
     * and create the parameters.
     * @param weight
     * @param leanMass
     * @param fatMass
     * @param email client mail
     * @return client parameter.
     * @throws IllegalArgumentException
     * @throws IOException
     */
    Parameters createParameters(String weight, String leanMass,
                                String fatMass, String email)
            throws IllegalArgumentException, IOException;

    /**
     * save the parameters into the database.
     * @param parameters the parameters to insert
     * @return true if the operation is done
     * @throws IOException
     */
    boolean saveParameters(
            Parameters parameters) throws IOException;

    /**
     * this method show the parameters for the input email.
     * @param email mail client who want to retrieve his parameters
     * @return list of parameters
     */
    ArrayList<Parameters> getByMail(
            String email) throws
            InterruptedException, ExecutionException, IOException;
}
