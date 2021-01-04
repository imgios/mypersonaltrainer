package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

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
    boolean createAppointment(String date, String time, String mail) throws
            IOException, ExecutionException, InterruptedException;

    /**
     *
     * @param appuntamneto appointment to delete
     * @return true if the appointment is successfully deleted
     */
    boolean removeAppointment(Appointment appuntamneto) throws
            InterruptedException, ExecutionException, IOException;

    /**
     *
     * @param date
     * @return return a list of appointments fixed on that date
     */
    List<Appointment> findAppointmentByDate(String date) throws
            InterruptedException, ExecutionException, IOException;


    /**
     * @param data the data to check
     * @param time the time to check
     * @return true if it's possible to insert the data
     * @throws IllegalArgumentException
     */
    boolean checkAvailability(String data, String time)
            throws IllegalArgumentException;

    /**
     * this method interact with the DAO to
     * insert the availability into database.
     *
     * @param availability the availability to insert into database
     * @return true if the availability was insert
     * @throws IOException
     */
    boolean createAvailability(Availability availability)
            throws IOException;

    /**
     * return the availability list from the date in input.
     *
     * @param date the date to find the availability
     * @return the availability list for this date.
     * @throws ExecutionException
     * @throws IOException
     * @throws InterruptedException
     */
    List<Availability> getAvailabilityByDate(
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
    boolean removeAvailability(
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
    Availability getAvailabilityByDateAndTime(
            String date, int time)
            throws IOException, InterruptedException, ExecutionException;




}
