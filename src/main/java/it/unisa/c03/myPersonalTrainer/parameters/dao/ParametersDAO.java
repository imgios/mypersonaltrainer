package it.unisa.c03.myPersonalTrainer.parameters.dao;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ParametersDAO {

    /**
     * this method interact with database to insert the parameters.
     *
     * @param param the parameters to insert into database
     * @throws IOException
     */
    void insertParameters(Parameters param) throws IOException;



    /**
     * retrieve the client parameters
     *
     * @param email
     * @return List of parameters of this client mail
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    ArrayList<Parameters> selectByMail(String email)
            throws IOException, ExecutionException, InterruptedException;
}
