package it.unisa.c03.myPersonalTrainer.trainingplan.bean;



public class TrainingPlan {
    private String exercises;
    private String date;
    private String email;

    @Override
    public String toString() {
        return "TrainingPlan{" +
                "exercises='" + exercises + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public TrainingPlan() {

    }

    public TrainingPlan(String exercises, String date, String email) {
        this.exercises = exercises;
        this.date = date;
        this.email = email;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExercises() {
        return exercises;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }
}
