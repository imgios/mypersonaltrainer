package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class AgendaServiceImpl implements AgendaService {

    /**
     * max possible value for time.
     */
    private static final int MAX = 19;
    /**
     * min possible value for time.
     */
    private static final int MIN = 9;
    /**
     * variable initialization to execute CRUD operation.
     */
    private AgendaDAO dao;

    public AgendaServiceImpl(AgendaDAO agendaDAO) {
        dao = agendaDAO;
    }

    @Override
    public boolean checkDate(String date) {
        LocalDate data = LocalDate.parse(date);
        boolean check = data.isAfter(
                LocalDate.now()) || data.isEqual(LocalDate.now());
        return check;
    }

    @Override
    public boolean createAppointment(String date, String time, String mail)
            throws IOException, ExecutionException, InterruptedException {
        int num = Integer.valueOf(time);
        boolean datacheck = checkDate(date);
        boolean timecheck = num <= MAX && num >= MIN;
        boolean mailcheck = mail.matches(
                "\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$");
        if (datacheck && timecheck && mailcheck) {
            Appointment appuntamento = new Appointment(date, time, mail);
            dao.deleteAvailability(dao.
                    findAvailabilityByDateAndTime(date, num));
            return dao.saveAppointment(appuntamento);
        }
        return false;
    }

    @Override
    public boolean removeAppointment(Appointment appuntamento) throws
            InterruptedException, ExecutionException, IOException {
        dao.deleteappointment(appuntamento);
        return true;
    }

    @Override
    public List<Appointment> findAppointmentByDate(String date) throws
            InterruptedException, ExecutionException, IOException {
        boolean datacheck = checkDate(date);
        if (datacheck) {
            return dao.findAppointmentByDate(date);
        }
        return null;
    }


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

        return dao.insertAvailability(availability);
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

        return dao.findAvailabilityByDate(date);
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
        return dao.deleteAvailability(availability);
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
        return dao.findAvailabilityByDateAndTime(date, time);
    }
}
