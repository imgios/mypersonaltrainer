package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;

import java.io.IOException;
import java.time.LocalDate;

public class AgendaServiceImpl implements AgendaService {

    private static final int MIN_TIME = 9;
    private static final int MAX_TIME = 19;

    private AgendaDAO agendaDAO = new AgendaDAOImpl();

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

    @Override
    public boolean createAvailability(Availability availability)
            throws IOException {

        return agendaDAO.insertAvailability(availability);
    }
}
