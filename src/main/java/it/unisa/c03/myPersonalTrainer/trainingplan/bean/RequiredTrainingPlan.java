package it.unisa.c03.myPersonalTrainer.trainingplan.bean;

import java.io.Serializable;

/**
 * account class.
 */
public class RequiredTrainingPlan implements Serializable {

    /**
     * user's email.
     */
    private String email;

    /**
     * required flag to identify the
     * TrainingPlan's request.
     */
    private int required;

    /**
     * method for get the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * method for set the email.
     * @param newemail email
     */
    public void setEmail(String newemail) {
        this.email = newemail;
    }

    /**
     * method for get the required flag.
     * @return the password
     */
    public int getRequired() {
        return required;
    }

    /**
     * method for set the required flag.
     * @param newrequired training plan required
     */
    public void setRequired(int newrequired) {
        this.required = newrequired;
    }

    /**
     * constructor of the class.
     */
    public RequiredTrainingPlan() {
    }

    /**
     * param costructor.
     * @param newemail new email
     * @param newrequired new flag required
     */
    public RequiredTrainingPlan(String newemail, int newrequired) {
        this.email = newemail;
        this.required = newrequired;
    }

    /**
     * method to string, to view the information during a stamp.
     */
    @Override
    public String toString() {
        return "RequiredTrainingPlan{"
                + "eMail='" + email + '\''
                + ", required=" + required
                + '}';
    }
}
