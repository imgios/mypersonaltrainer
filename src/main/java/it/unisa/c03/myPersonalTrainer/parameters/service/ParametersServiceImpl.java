package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ParametersServiceImpl implements ParametersService {

    /**
     * the MIN_WEIGHT allowed
     */
    private static final int MIN_WEIGHT = 40;
    /**
     * the MAX_WEIGHT allowed
     */
    private static final int MAX_WEIGHT = 150;
    /**
     * the MIN_PERCENTAGE allowed
     */
    private static final int MIN_PERCENTAGE = 10;
    /**
     * the MAX_PERCENTAGE allowed
     */
    private static final int MAX_PERCENTAGE = 70;
    /**
     * the MIN_LENGHT for the WEIGHT allowed
     */
    private static final int MIN_LENGHT_WEIGHT = 2;
    /**
     * the MAX_LENGHT for the WEIGHT allowed
     */
    private static final int MAX_LENGHT_WEIGHT = 6;
    /**
     * the PERCENTAGE
     */
    private static final int PERCENTAGE = 100;


    private ParametersDAO parametersDAO;

    public ParametersServiceImpl(
            ParametersDAO parametersDAO) {
        this.parametersDAO = parametersDAO;
    }

    /**
     * check the format parameters
     *
     * @param weight   between 40 and 150 format allowed XXX.XX
     * @param leanMass between 10% and 70%
     * @param fatMass  between 10% and 70%
     * @return the parameters, null if the parameters in input are not good
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    public Parameters createParameters(
            String weight, String leanMass, String fatMass)
            throws IllegalArgumentException, IOException {

        if (weight == null || leanMass == null || fatMass == null) {
            throw new IllegalArgumentException(
                    "valori mancanti");
        }

        if (weight.length() < MIN_LENGHT_WEIGHT
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

        if (weightD < MIN_WEIGHT || weightD > MAX_WEIGHT) {
            throw new NumberFormatException(
                    "valore peso non valido");
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
        double fatMassTotal =
                (weightD / PERCENTAGE) * fatMassD;
        double leanMassTotal =
                (weightD / PERCENTAGE) * leanMassD;
        BigDecimal bigDecimalleanMassTot =
                new BigDecimal(Double.toString(leanMassTotal));
        BigDecimal bigDecimalfatMassTot =
                new BigDecimal(Double.toString(fatMassTotal));
        bigDecimalleanMassTot =
                bigDecimalleanMassTot.setScale(
                        1, RoundingMode.HALF_UP);
        bigDecimalfatMassTot =
                bigDecimalfatMassTot.setScale(
                        1, RoundingMode.HALF_UP);
        fatMassTotal =
                bigDecimalfatMassTot.doubleValue();
        leanMassTotal =
                bigDecimalleanMassTot.doubleValue();
        Parameters pa =
                new Parameters(weightD,
                        fatMassTotal, leanMassTotal, "prova@io.it");
        return pa;
    }

    /**
     * @param parameters the parameters to insert
     * @return true if the parameters are inserted into database
     * @throws IOException
     */
    @Override
    public boolean insertParametersDB(
            Parameters parameters) throws IOException {
        return parametersDAO.insertParameters(parameters);
    }

    /**
     * @param email mail client who want to retrieve his parameters
     * @return list of parameters
     */
    public ArrayList<Parameters> getByMail(
            String email) throws InterruptedException,
            ExecutionException, IOException {
        if (email == null) {
            throw new IllegalArgumentException(
                    "Email non valida");
        }
        ArrayList<Parameters> list =
                parametersDAO.selectByMail(email);
        return list;
    }
}
