package it.unisa.c03.myPersonalTrainer.GestioneParametri.model.bean;

import java.text.DateFormat;
import java.util.Date;

public class Parameters {
    float weight, leanMass, fatMass;
    String mailClient;
    String insertionDate;

    public float getweight() {
        return weight;
    }

    public Parameters(float weight, float leanMass, float fatMass, String mailClient) {
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
        return "Parameters{" +
                "weight=" + weight +
                ", leanMass=" + leanMass +
                ", fatMass=" + fatMass +
                ", mailClient='" + mailClient + '\'' +
                ", insertionDate=" + insertionDate +
                '}';
    }

    public void setweight(float weight) {
        this.weight = weight;
    }

    public float getleanMass() {
        return leanMass;
    }

    public void setleanMass(float leanMass) {
        this.leanMass = leanMass;
    }

    public float getfatMass() {
        return fatMass;
    }

    public void setfatMass(float fatMass) {
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
