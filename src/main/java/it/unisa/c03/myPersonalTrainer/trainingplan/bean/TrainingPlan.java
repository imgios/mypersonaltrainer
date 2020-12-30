package it.unisa.c03.myPersonalTrainer.trainingplan.bean;


import java.time.LocalDate;

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

    public TrainingPlan(String exercises, String email) {
        this.exercises = exercises;
        LocalDate date = LocalDate.now();
        String dataaa = date.toString();
        this.date = dataaa;
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
