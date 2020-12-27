package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ParametersServiceImpl implements ParametersService {
    private static final int MIN_WEIGHT = 40;
    private static final int MAX_WEIGHT = 150;
    private static final int MIN_PERCENTAGE = 10;
    private static final int MAX_PERCENTAGE = 70;
    private static final int MIN_LENGHT_WEIGHT = 2;
    private static final int MAX_LENGHT_WEIGHT = 6;
    private static final int PERCENTAGE = 100;


    private final ParametersDAO parametersDAO = new ParametersDAOImpl();

    /**
     * allows to check the format parameters, and to insert into database.
     *
     * @param weight   between 40 and 150 format alloed XXX.XX
     * @param leanMass between 10% and 70%
     * @param fatMass  between 10% and 70%
     * @return the added parameters
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    public Parameters createParameters(String weight, String leanMass,
                                       String fatMass)
            throws NumberFormatException, IllegalArgumentException {

        if (weight != null
                && weight.length() < MIN_LENGHT_WEIGHT
                || weight.length() > MAX_LENGHT_WEIGHT) {
            throw new IllegalArgumentException(
                    "lunghezza peso non valida");
        }
        if (!weight.matches(
                "([0-9]){2,3}\\.?[0-9]{0,2}?$")) {
            throw new NumberFormatException(
                    "formato peso non valido");
        }
        if (!fatMass.matches(
                "([0-9]+\\%){1,2}$")) {
            throw new NumberFormatException(
                    "formato massa grassa non valido");
        }
        if (!leanMass.matches(
                "([0-9]+\\%){1,2}$")) {
            throw new NumberFormatException(
                    "formato massa magra non valido");
        }
        double weightD = Double.parseDouble(weight);
        if (weightD < MIN_WEIGHT
                || weightD > MAX_WEIGHT) {
            throw new IllegalArgumentException(
                    "lunghezza peso non valida");
        }
        double leanMassD =
                Double.parseDouble(leanMass.substring(
                        0, leanMass.length() - 1));
        double fatMassD =
                Double.parseDouble(fatMass.substring(
                        0, fatMass.length() - 1));
        if (fatMassD < MIN_PERCENTAGE
                || fatMassD > MAX_PERCENTAGE) {
            throw new IllegalArgumentException(
                    "lunghezza massa grassa non valida");
        }
        if (leanMassD < MIN_PERCENTAGE
                || leanMassD > MAX_PERCENTAGE) {
            throw new IllegalArgumentException(
                    "lunghezza massa magra non valida");
        }
        double fatMassTotal = (weightD / PERCENTAGE) * fatMassD;
        double leanMassTotal = (weightD / PERCENTAGE) * leanMassD;
        BigDecimal bigDecimalleanMassTot =
                new BigDecimal(Double.toString(leanMassTotal));
        BigDecimal bigDecimalfatMassTot =
                new BigDecimal(Double.toString(fatMassTotal));
        bigDecimalleanMassTot =
                bigDecimalleanMassTot.setScale(1, RoundingMode.HALF_UP);
        bigDecimalfatMassTot =
                bigDecimalfatMassTot.setScale(1, RoundingMode.HALF_UP);
        fatMassTotal =
                bigDecimalfatMassTot.doubleValue();
        leanMassTotal =
                bigDecimalleanMassTot.doubleValue();
        Parameters pa =
                new Parameters(weightD,
                        fatMassTotal, leanMassTotal, "mail@io.it");
        parametersDAO.insertParameters(pa);
        return pa;
    }

    /**
     * @param email mail client who want to retrieve his parameters
     * @return list of parameters
     */
    public ArrayList<Parameters> getByMail(String email) {
        if (email == null) {
            throw new IllegalArgumentException(
                    "Email non valida");
        }
        ArrayList<Parameters> list = parametersDAO.selectByMail(email);
        return list;
    }
}