package it.unisa.c03.myPersonalTrainer.parameters.bean;

import java.text.DateFormat;
import java.util.Date;

/**
 * this class show the user parameters details.
 */
public class Parameters {
    private double weight;
    private double leanMass;
    private double fatMass;
    private String mailClient;
    private String insertionDate;

    public Parameters() {

    }

    public double getweight() {
        return weight;
    }

    public Parameters(double weight, double leanMass,
                      double fatMass, String mailClient) {
        this.weight = weight;
        this.leanMass = leanMass;
        this.fatMass = fatMass;
        this.mailClient = mailClient;

        Date d = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        String x = (df.format(d));
        this.insertionDate = x;
    }


    @Override
    public String toString() {
        return "Parameters{"
                + "weight=" + weight
                + ", leanMass=" + leanMass
                + ", fatMass=" + fatMass
                + ", mailClient='" + mailClient + '\''
                + ", insertionDate=" + insertionDate
                + '}';
    }

    public void setweight(double weight) {
        this.weight = weight;
    }

    public double getleanMass() {
        return leanMass;
    }

    public void setleanMass(double leanMass) {
        this.leanMass = leanMass;
    }

    public double getfatMass() {
        return fatMass;
    }

    public void setfatMass(double fatMass) {
        this.fatMass = fatMass;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public String getinsertionDate() {
        return insertionDate;
    }

    public void setinsertionDate(String insertionDate) {
        this.insertionDate = insertionDate;
    }
}
