package it.unisa.c03.myPersonalTrainer.control.service;

import it.unisa.c03.myPersonalTrainer.model.bean.Parameters;

public interface ParametersService {
    public Parameters acceptParameters(String weight, String leanMass, String fatMass) throws NumberFormatException, IllegalArgumentException;
}
