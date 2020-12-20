package it.unisa.c03.myPersonalTrainer;

import java.util.Date;

public class Parameters {
    float peso, massaMagra, massagGrassa;
    String mailClient;
    Date dataInserimento;

    public float getPeso() {
        return peso;
    }

    public Parameters(float peso, float massaMagra, float massagGrassa, String mailClient, Date dataInserimento) {
        this.peso = peso;
        this.massaMagra = massaMagra;
        this.massagGrassa = massagGrassa;
        this.mailClient = mailClient;
        this.dataInserimento = dataInserimento;
    }



    @Override
    public String toString() {
        return "Parameters{" +
                "peso=" + peso +
                ", massaMagra=" + massaMagra +
                ", massagGrassa=" + massagGrassa +
                ", mailClient='" + mailClient + '\'' +
                ", dataInserimento=" + dataInserimento +
                '}';
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(float massaMagra) {
        this.massaMagra = massaMagra;
    }

    public float getMassagGrassa() {
        return massagGrassa;
    }

    public void setMassagGrassa(float massagGrassa) {
        this.massagGrassa = massagGrassa;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
}
