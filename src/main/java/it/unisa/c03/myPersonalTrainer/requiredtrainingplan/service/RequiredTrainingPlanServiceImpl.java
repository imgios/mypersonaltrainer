package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service;

import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RequiredTrainingPlanServiceImpl implements
        RequiredTrainingPlanService {
    /**
     * Object RequiredTrainingPlanDAO to work into the Service.
     */
    private RequiredTrainingPlanDAO requiredTrainingPlanDAO;
    /**
     * Service constructor.
     * @param requiredTrainingPlanDao is required, because is the DAO that
     * all the service methods will call.
     **/
    public RequiredTrainingPlanServiceImpl(
            RequiredTrainingPlanDAO requiredTrainingPlanDao) {
        requiredTrainingPlanDAO = requiredTrainingPlanDao;
    }

    /**
     * This service method checks if an account exists in the database.
     * @param email referring to the account to search for
     * @return true if the account exists, false if not
     */
    @Override
    public boolean searchAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException {

        boolean result = false;
        RequiredTrainingPlan requiredTrainingPlan = new RequiredTrainingPlan();
        requiredTrainingPlan =
                requiredTrainingPlanDAO.findAccountByEmail(email);

        //the email exists yet in the DB
        if (requiredTrainingPlan.getEmail() != null) {
            result = true;
        } else if (requiredTrainingPlan.getEmail() == null) {
            //the email doesn't exist in the DB
            result = false;
        }
        return result;
    }

    /**
     * This method return the account given its mail.
     * @param email referring to the account to search for
     * @return the Account if it exists, null if not
     */
    @Override
    public RequiredTrainingPlan getAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException {
        RequiredTrainingPlan requiredTrainingPlan = new RequiredTrainingPlan();
        requiredTrainingPlan =
                requiredTrainingPlanDAO.findAccountByEmail(email);

        //the email exists in the DB
        if (requiredTrainingPlan.getEmail() != null) {
            return requiredTrainingPlan;
        } else if (requiredTrainingPlan.getEmail() == null) {
            //the email doesn't exist in the DB
            return null;
        }

        return null;
    }

    /**
     * This service method changes the password of an account.
     * @param email of the account
     * @param status updated
     * @return true after the change has taken place
     */
    @Override
    public boolean changeRequest(String email, int status) //changeRequest
            throws IOException, ExecutionException, InterruptedException {

        return requiredTrainingPlanDAO.updateRequest(email, status);
    }

    /**
     * this function register the account.
     * @param requiredTrainingPlan user into the db.
     * @return utente user mem into the db.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Override
    public boolean registerRequest(RequiredTrainingPlan requiredTrainingPlan)
            throws IOException, IllegalArgumentException,
            ExecutionException, InterruptedException {

        RequiredTrainingPlan ricerca;
        ricerca = requiredTrainingPlanDAO
                .findAccountByEmail(requiredTrainingPlan.getEmail());

        if (ricerca.getEmail() == null) {
            requiredTrainingPlanDAO.storeRequest(requiredTrainingPlan);
            return true;
        } else {
            return false;
        }
    }
}
