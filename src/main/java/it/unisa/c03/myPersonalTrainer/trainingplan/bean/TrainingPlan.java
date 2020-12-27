package it.unisa.c03.myPersonalTrainer.trainingplan.bean;

import java.util.Date;

public class TrainingPlan {

    @Override
    public String toString() {

        return "trainingPlan{" +
                "exercises=" +
                exercises +
                ", date=" +
                date +
                ", email='" +
                email +
                '\'' +
                '}';
    }

    public TrainingPlan(){

    }

    public TrainingPlan(String exercises, Date date, String email) {
        this.exercises = exercises;
        this.date = date;
        this.email = email;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExercises() {
        return exercises;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    private String exercises;
    private Date date;
    private String email;

}
