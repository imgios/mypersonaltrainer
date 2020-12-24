package it.unisa.c03.myPersonalTrainer.GestioneParametri.service;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;

import java.util.ArrayList;

public interface ParametersService {
     Parameters createParameters(String weight, String leanMass, String fatMass) throws NumberFormatException, IllegalArgumentException;
    ArrayList<Parameters> getByMail(String email);
}
