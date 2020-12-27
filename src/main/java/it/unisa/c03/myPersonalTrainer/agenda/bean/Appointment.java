package it.unisa.c03.myPersonalTrainer.agenda.bean;

import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public final class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * appointment's date.
     */
    private Date date;
    /**
     * appointment's time.
     */
    private String time;
    /**
     * appointment's applicant.
     */
    private String customerMail;

    /**
     * constructor.
     */
    public Appointment() {
    }

    /**
     *
     * parameterized constructor.
     * @param newdate appointment's date
     * @param newtime appointment's
     * @param newcustomerMail appliant's mail
     */
    public Appointment(final Date newdate, final String newtime,
                       final String newcustomerMail) {
        this.date = newdate;
        this.time = newtime;
        this.customerMail = newcustomerMail;
    }

    /**
     *
     * @return appoinment's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param newdate date of new appointment
     */
    public void setDate(final Date newdate) {
        this.date = newdate;
    }

    /**
     * @return appointment's time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param newtime time of new appointment
     */
    public void setTime(final String newtime) {
        this.time = newtime;
    }

    /**
     *
     * @return Appliant's mail
     */
    public String getCustomerMail() {
        return customerMail;
    }

    /**
     *
     * @param newcustomerMail the email of who request
     */
    public void setCustomerMail(final String newcustomerMail) {
        this.customerMail = newcustomerMail;
    }
}
