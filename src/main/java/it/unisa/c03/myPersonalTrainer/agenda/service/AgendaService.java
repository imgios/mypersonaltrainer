package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * this interface show the service methods.
 */
public interface AgendaService {
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
