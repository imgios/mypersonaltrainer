package it.unisa.c03.myPersonalTrainer.GestioneParametri.service;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.dao.ParametersDAOImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ParametersServiceImpl implements ParametersService {


    private ParametersDAO parametersDAO = new ParametersDAOImpl();
    /**
     * @param weight   compreso tra 40 e 150 formato consentito XXX.XX
     * @param leanMass compreso tra 10% e 70%
     * @param fatMass  compreso tra 10% e 70%
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    public Parameters createParameters(String weight, String leanMass, String fatMass) throws NumberFormatException, IllegalArgumentException {

        if (weight != null && weight.length() < 2 || weight.length() > 6)
            throw new IllegalArgumentException("lunghezza peso non valida");

        if (!weight.matches("([0-9]){2,3}\\.?[0-9]{0,2}?$"))
            throw new NumberFormatException("formato peso non valido");
        if (!fatMass.matches("([0-9]+\\%){1,2}$"))
            throw new NumberFormatException("formato massa grassa non valido");
        if (!leanMass.matches("([0-9]+\\%){1,2}$")) throw new NumberFormatException("formato massa magra non valido");

        double weightD = Double.parseDouble(weight);
        if (weightD < 40 || weightD > 150) throw new IllegalArgumentException("lunghezza peso non valida");

        double leanMassD = Double.parseDouble(leanMass.substring(0, leanMass.length() - 1));
        double fatMassD = Double.parseDouble(fatMass.substring(0, fatMass.length() - 1));
        if (fatMassD < 10 || fatMassD > 70)
            throw new IllegalArgumentException("lunghezza massa grassa non valida");
        if (leanMassD < 10 || leanMassD > 70) throw new IllegalArgumentException("lunghezza massa magra non valida");

        double fatMassTotal = (weightD / 100) * fatMassD;
        double leanMassTotal = (weightD / 100) * leanMassD;

        BigDecimal bigDecimalleanMassTot = new BigDecimal(Double.toString(leanMassTotal));
        BigDecimal bigDecimalfatMassTot = new BigDecimal(Double.toString(fatMassTotal));
        bigDecimalleanMassTot = bigDecimalleanMassTot.setScale(1, RoundingMode.HALF_UP);
        bigDecimalfatMassTot = bigDecimalfatMassTot.setScale(1, RoundingMode.HALF_UP);
        fatMassTotal = bigDecimalfatMassTot.doubleValue();
        leanMassTotal = bigDecimalleanMassTot.doubleValue();

        Parameters pa = new Parameters(weightD, fatMassTotal, leanMassTotal, "mail@io.it");
        parametersDAO.insertParameters(pa);

        //System.out.println(parametersDAO.getByMail("prova@io.it"));

        return pa;

    }
    /**
     *
     * @param email mail client who want to retrieve his parameters
     * @return list of parameters
     */
    public ArrayList<Parameters> getByMail(String email){

        return (parametersDAO.selectByMail(email));

    }

}
