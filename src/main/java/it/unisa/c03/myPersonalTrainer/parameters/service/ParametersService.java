package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;

public interface ParametersService {
    /**
     * this methods check if the parameters are acceptable; then enable to insert them into database.
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

    /**
     * insert the parameters into database.
     *
     * @param parameters the parameters to insert
     * @return true if the operation is done
     * @throws IOException
     */
    boolean insertParametersDB(Parameters parameters) throws IOException;

    /**
     * @param email mail client who want to retrieve his parameters
     * @return list of parameters
     */
    ArrayList<Parameters> getByMail(String email);
}
