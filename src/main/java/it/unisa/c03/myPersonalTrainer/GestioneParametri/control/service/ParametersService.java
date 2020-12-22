package it.unisa.c03.myPersonalTrainer.GestioneParametri.control.service;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.model.bean.Parameters;

public interface ParametersService {
    public Parameters createParameters(String weight, String leanMass, String fatMass) throws NumberFormatException, IllegalArgumentException;
}
