package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;

import java.io.IOException;

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
     * this method interact with the DAO to insert the availability into database.
     *
     * @param availability the availability to insert into database
     * @throws IOException
     */
    void createAvailability(Availability availability)
            throws IOException;
}
