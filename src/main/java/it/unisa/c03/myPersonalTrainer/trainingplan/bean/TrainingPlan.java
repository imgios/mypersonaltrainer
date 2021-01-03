package it.unisa.c03.myPersonalTrainer.trainingplan.bean;

import java.time.LocalDate;

/**
 * TrainingPlan class.
 */
public class TrainingPlan {

    /**
     * trainingplan's exercises.
     */
    private String exercises;
    /**
     * trainingplan's date.
     */
    private String date;
    /**
     * trainingplan's recipient.
     */
    private String email;

    /**
     * method to string, to view the information during a stamp.
     * @return String
     */
    @Override
    public String toString() {
        return "TrainingPlan{"
                + "exercises='"
                + exercises
                + '\''
                + ", date='"
                + date
                + '\''
                + ", email='"
                + email
                + '\''
                + '}';
    }

    /**
     * constructor of the class.
     */
    public TrainingPlan() {

    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrainingPlan that = (TrainingPlan) o;

        if (!exercises.equals(that.exercises)) {
            return false;
        }
        if (!date.equals(that.date)) {
            return false;
        }
        return email.equals(that.email);
    }
*/

    /**
     * constructor of the class.
     * @param exercises
     * @param email
     */
    public TrainingPlan(String exercises, String email) {
        this.exercises = exercises;
        LocalDate date = LocalDate.now();
        String dataaa = date.toString();
        this.date = dataaa;
        this.email = email;
    }

    /**
     * method for set exercises.
     * @param exercises
     */
    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    /**
     * method for set date.
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * method for set email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * method for get exercises.
     * @return String
     */
    public String getExercises() {
        return exercises;
    }

    /**
     * method for get the date.
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * method for get email.
     * @return String
     */
    public String getEmail() {
        return email;
    }

}