package it.unisa.c03.myPersonalTrainer.agenda.bean;

import java.time.LocalDate;

public class Avaiability {

    private LocalDate date;
    private int time;

    public Avaiability(LocalDate date, int time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Avaiability{"
                + "date=" + date
                + ", time=" + time
                + '}';
    }

    public Avaiability() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
