package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

public class TrainingPlanServiceImpl implements TrainingPlanService {
/*
    @Override
    public TrainingPlan createTrainingPlan() {

    }
*/
    @Override
    public boolean checkExercise(String exercise, String repetitions, String series, String recoveryTime)  throws IllegalArgumentException {

        boolean result = false ;

        if(exercise.length() < 4 || exercise.length() > 25) {
            throw new IllegalArgumentException("invalid exercise");
        }

        else if(!(exercise.matches(" /^[a-zA-z]+$/"))) {
            throw  new IllegalArgumentException("invalid exercise");
        }

        else if( series.length() >= 1 || series.length() <= 3) {
            throw new IllegalArgumentException("invalid repeats");
        }

        else if(!(series.matches(" /^[0-9]+$/"))){
            throw  new IllegalArgumentException("invalid exercise");
        }

        else if( repetitions.length() >= 1 || repetitions.length() <= 3) {
            throw new IllegalArgumentException("invalid repeats");
        }

        else if(!(repetitions.matches(" /^[0-9]+$/"))) {
            throw  new IllegalArgumentException("invalid exercise");
        }

        else if( !(recoveryTime.length() >= 1 || recoveryTime.length() <= 3)) {
            throw new IllegalArgumentException("invalid repeats");
        }

        else if(!(recoveryTime.matches(" /^[0-9]+$/"))) {
            throw  new IllegalArgumentException("invalid exercise");
        }

        else {
            result = true ;
        }

        return result ;

    }

}
