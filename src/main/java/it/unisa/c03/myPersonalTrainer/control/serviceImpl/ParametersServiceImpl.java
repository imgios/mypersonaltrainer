package it.unisa.c03.myPersonalTrainer.control.serviceImpl;

import it.unisa.c03.myPersonalTrainer.control.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.model.bean.Parameters;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ParametersServiceImpl implements ParametersService {
    /**
     * @param weight   compreso tra 40 e 150
     * @param leanMass compreso tra 10% e 70%
     * @param fatMass  compreso tra 10% e 70%
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    public Parameters acceptParameters(String weight, String leanMass, String fatMass) throws NumberFormatException, IllegalArgumentException {

        if (weight != null && weight.length() < 2 || weight.length() > 3)
            throw new IllegalArgumentException("lenght weight non valida");

        if (!weight.matches("([0-9]){2,3}\\.?[0-9]{0,2}?$"))
            throw new NumberFormatException("format weight non valido");
        if (!fatMass.matches("([0-9]+\\%){1,2}$"))
            throw new NumberFormatException("format fatMass non valido");
        if (!leanMass.matches("([0-9]+\\%){1,2}$")) throw new NumberFormatException("format leanMass non valido");

        float weightF = Float.parseFloat(weight);
        if (weightF < 40 || weightF > 150) throw new IllegalArgumentException("lenght weight non valida");

        float leanMassF = Float.parseFloat(leanMass.substring(0, leanMass.length() - 1));
        float fatMassF = Float.parseFloat(fatMass.substring(0, fatMass.length() - 1));
        if (fatMassF < 10 || fatMassF > 70)
            throw new IllegalArgumentException("lenght fatMass non valida");
        if (leanMassF < 10 || leanMassF > 70) throw new IllegalArgumentException("lenght leanMass non valida");

        float leanMassTot = (weightF / 100) * leanMassF;
        float fatMassTot = (weightF / 100) * fatMassF;

        BigDecimal bigDecimalleanMassTot = new BigDecimal(Float.toString(leanMassTot));
        BigDecimal bigDecimalfatMassTot = new BigDecimal(Float.toString(fatMassTot));
        bigDecimalleanMassTot = bigDecimalleanMassTot.setScale(1, RoundingMode.HALF_UP);
        bigDecimalfatMassTot = bigDecimalfatMassTot.setScale(1, RoundingMode.HALF_UP);
        fatMassTot = bigDecimalfatMassTot.floatValue();
        leanMassTot = bigDecimalleanMassTot.floatValue();

        Parameters pa = new Parameters(weightF, fatMassTot, leanMassTot, "mail@io.it");
        return pa;
    }

}
