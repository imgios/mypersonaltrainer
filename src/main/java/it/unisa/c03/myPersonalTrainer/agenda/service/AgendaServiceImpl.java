package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class AgendaServiceImpl implements AgendaService {

    /**
     * max possible value for time.
     */
    private static final int MAX = 24;
    /**
     * min possible value for time.
     */
    private static final int MIN = 0;
    /**
     *variable initialization to execute CRUD operation.
     */
    private AgendaDAO dao;
    public AgendaServiceImpl(AgendaDAO agendaDAO) {
        dao = agendaDAO;
    }
    @Override
    public boolean checkDate(String date) {
        LocalDate data = LocalDate.parse(date);
        boolean check = data.isAfter(LocalDate.now());
        return check;
    }

    @Override
    public boolean createAppointment(String date, String time, String mail)
            throws IOException {
        int num= Integer.valueOf(time);
        boolean datacheck = checkDate(date);
        boolean timecheck = num <= MAX && num >= MIN;
        boolean mailcheck = mail.matches(
                "\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$");
        if (datacheck && timecheck && mailcheck) {
            Appointment appuntamento = new Appointment(date, time, mail);
            return dao.saveAppointment(appuntamento);
        }
        return false;
    }

    @Override
    public boolean removeAppointment(Appointment appuntamento) throws InterruptedException, ExecutionException, IOException {
        dao.deleteappointment(appuntamento);
        return true;
    }

    @Override
    public List<Appointment> findAppointmentByDate(String date) throws InterruptedException, ExecutionException, IOException {
        boolean datacheck = checkDate(date);
        if (datacheck) {
            return dao.findAppointmentByDate(date);
        }
        return null;
    }
}
