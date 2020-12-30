package it.unisa.c03.myPersonalTrainer.agenda.dao;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AgendaDAO {
    /**
     *
     * @param appuntamento new appointment to insert in the database
     * @return return true if the appointment is saved,false otherwise
     */
    boolean saveAppointment(Appointment appuntamento) throws IOException;
    /**
     *
     * @param mail customer's email
     * @return return all the customer's appointment
     */
    List<Appointment> findAppointmetsByEmail(String mail)
            throws IOException, ExecutionException, InterruptedException;

    /**
     *
     * @param date date to check appointment
     * @return returns all appointments for a given date
     */
    List<Appointment> findAppointmentByDate(String date) throws
            IOException, ExecutionException, InterruptedException;

    /**
     *
     * @param appuntamento appointment to delete
     * @return true if the appointment is deleted
     */
    boolean deleteappointment(Appointment appuntamento) throws IOException, ExecutionException, InterruptedException;
}
