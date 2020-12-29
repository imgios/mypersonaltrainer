package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * this class implements all service methods.
 */
public class AgendaServiceImpl
        implements AgendaService {
    /**
     * define the min time to make availability.
     */
    private static final int MIN_TIME = 9;
    /**
     * define the max time to make availability.
     */
    private static final int MAX_TIME = 19;
    /**
     * define the agendaDao.
     */
    private AgendaDAO agendaDAO;

    /**
     *  initialize an agendaDAO object.
     * @param agendaDAO
     */
    public AgendaServiceImpl(AgendaDAO agendaDAO) {
        this.agendaDAO = agendaDAO;
    }

    /**
     * this class check if the data and the time format
     * are good to be insert into database.
     *
     * @param data the data to check
     * @param time the time to check
     * @return true if the check success, false otherwise.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean checkAvailability(String data, String time)
            throws IllegalArgumentException {

        boolean result = false;
        if (!data.matches(
                "^((19|2[0-9])[0-9]{2})-"
                        + "(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
            throw new IllegalArgumentException(
                    "Formato data non valido");
        } else {
            LocalDate dateL = LocalDate.parse(data);
            if (dateL.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "Data precedente a quella odierna");
            }
            if (!time.matches("([0-9]){1,2}")) {
                throw new IllegalArgumentException(
                        "Formato orario non valido");
            } else if (Integer.parseInt(time) < MIN_TIME
                    || Integer.parseInt(time) > MAX_TIME) {
                throw new IllegalArgumentException(
                        "Orario non valido");
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * this methods enables to insert an availability into database.
     *
     * @param availability the availability to insert into database
     * @return true if the insertion is done; else otherwise.
     * @throws IOException
     */
    @Override
    public boolean createAvailability(Availability availability)
            throws IOException {

        return agendaDAO.insertAvailability(availability);
    }

    /**
     * return the availability list from the date in input.
     *
     * @param date the date to find the availability
     * @return
     * @throws ExecutionException
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public List<Availability> getAvailabilityByDate(
            String date)
            throws ExecutionException, IOException, InterruptedException {

        return agendaDAO.findAvailabilityByDate(date);
    }

    /**
     * enable to remove the availability.
     *
     * @param availability the availability to remove
     * @return true if the remove is done.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public boolean removeAvailability(Availability availability)
            throws IOException, ExecutionException, InterruptedException {
        return agendaDAO.deleteAvailability(availability);
    }

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
    @Override
    public Availability getAvailabilityByDateAndTime(
            String date, int time)
            throws IOException, InterruptedException, ExecutionException {
        return agendaDAO.findAvailabilityByDateAndTime(date, time);
    }
}