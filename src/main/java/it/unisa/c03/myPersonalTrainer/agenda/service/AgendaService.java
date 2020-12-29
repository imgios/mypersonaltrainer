package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AgendaService {
    /**
     *check if the appointment date is valid.
     * @param date date to check
     * @return return true if the date is valid
     */
    boolean checkDate(String date);

    /**
     *creates the appointment and makes it persistent in the database.
     * @param date appointment's date
     * @param time appointment's mail
     * @param mail appointment's applicant
     * @return return true if the insertion is successful
     */
    boolean createAppointment(String date, String time, String mail) throws IOException;

    /**
     *
     * @param appuntamneto appointment to delete
     * @return true if the appointment is successfully deleted
     */
    boolean removeAppointment(Appointment appuntamneto) throws InterruptedException, ExecutionException, IOException;

    /**
     *
     * @param date
     * @return return a list of appointments fixed on that date
     */
    List<Appointment> findAppointmentByDate(String date) throws InterruptedException, ExecutionException, IOException;
}
