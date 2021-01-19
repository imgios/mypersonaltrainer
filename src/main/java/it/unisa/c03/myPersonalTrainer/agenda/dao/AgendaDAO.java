package it.unisa.c03.myPersonalTrainer.agenda.dao;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

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
    boolean deleteappointment(Appointment appuntamento) throws
            IOException, ExecutionException, InterruptedException;


    /**
     * interact with database to insert the availability.
     * @return true if the insertion is done, false otherwise.
     * @param availability the availability to insert into database
     * @throws IOException
     */
    boolean insertAvailability(Availability availability) throws IOException;

    /**
     * return the availability list from the date in input.
     *
     * @param date for find availability
     * @return availability list for this date
     * @throws ExecutionException
     * @throws IOException
     * @throws InterruptedException
     */
    List<Availability> findAvailabilityByDate(
            String date)
            throws ExecutionException, IOException, InterruptedException;

    /**
     * enable to remove the availability.
     *
     * @param availability the availability to remove
     * @return true if the remove is done.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean deleteAvailability(
            Availability availability)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * retrieve the availability with this  time and date.
     *
     * @param date
     * @param time
     * @return the availability that contains this time and date.
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    Availability findAvailabilityByDateAndTime(
            String date, int time)
            throws IOException, InterruptedException, ExecutionException;


}
