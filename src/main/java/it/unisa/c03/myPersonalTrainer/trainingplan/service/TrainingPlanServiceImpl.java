package it.unisa.c03.myPersonalTrainer.trainingplan.service;

public class TrainingPlanServiceImpl implements TrainingPlanService {


    /*
        @Override
        public TrainingPlan createTrainingPlan() {

        }
    */
    @Override
    public boolean checkExercise(String exercise, String repetitions, String series, String recoveryTime) throws IllegalArgumentException {

        boolean result = false;

        if (!(exercise.matches(" /^[a-zA-z]+$/"))) {
            throw new IllegalArgumentException("invalid exercise");
        } else if (exercise.length() < 4 || exercise.length() > 25) {
            throw new IllegalArgumentException("invalid exercise length");
        } else if (!(series.matches(" /^[0-9]+$/"))) {
            throw new IllegalArgumentException("invalid exercise format");
        } else if (Integer.parseInt(series) < 1 || Integer.parseInt(series) >= 20) {
            throw new IllegalArgumentException("invalid series length");
        } else if (!(repetitions.matches(" /^[0-9]+$/"))) {
            throw new IllegalArgumentException("invalid repetitions format");
        } else if (Integer.parseInt(repetitions) < 1 || Integer.parseInt(repetitions) >= 50) {
            throw new IllegalArgumentException("invalid repetitions length");
        } else if (!(recoveryTime.matches(" /^[0-9]+$/"))) {
            throw new IllegalArgumentException("invalid recoveryTime format");
        } else if (Integer.parseInt(recoveryTime) < 1 || Integer.parseInt(recoveryTime) >= 300) {
            throw new IllegalArgumentException("invalid recoveryTime length");
        } else {
            result = true;
        }

        return result;
    }
}
