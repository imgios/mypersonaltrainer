package it.unisa.c03.myPersonalTrainer.agenda.dao;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * this class provides the methods to make the CRUD operations into database.
 */
public interface AgendaDAO {


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
