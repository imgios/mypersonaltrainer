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
    boolean insertParameters(
            Parameters param) throws IOException;

    /***
     *
     * @param email client mail to retrieve his parameters
     * @return a list of client parameters
     */
    ArrayList<Parameters> selectByMail(
            String email) throws
            InterruptedException, IOException, ExecutionException;
}
