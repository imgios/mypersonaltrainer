package it.unisa.c03.myPersonalTrainer.agenda.dao;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AgendaDAO {


    /**
     * interact with database to insert the availability .
     *
     * @param availability the availability to insert into database
     * @throws IOException
     */
    boolean insertAvailability(Availability availability) throws IOException;
}
