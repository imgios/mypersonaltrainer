package it.unisa.c03.myPersonalTrainer.agenda.bean;

import java.io.Serializable;

public class Availability implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date;
    private int time;

    public Availability(String date, int time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Availability{"
                + "date=" + date
                + ", time=" + time
                + '}';
    }

    public Availability() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
