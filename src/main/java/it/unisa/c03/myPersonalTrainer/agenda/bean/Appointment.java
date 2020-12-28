package it.unisa.c03.myPersonalTrainer.agenda.bean;
import java.io.Serializable;

public final class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * appointment's date.
     */
    private String date;
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
    public Appointment(String newdate, String newtime,
                        String newcustomerMail) {
        this.date = newdate;
        this.time = newtime;
        this.customerMail = newcustomerMail;
    }

    /**
     *
     * @return appoinment's date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param newdate date of new appointment
     */
    public void setDate(String newdate) {
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
    public void setTime(String newtime) {
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
    public void setCustomerMail(String newcustomerMail) {
        this.customerMail = newcustomerMail;
    }
}
