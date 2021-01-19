package it.unisa.c03.myPersonalTrainer.agenda.dao;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


class AgendaDAOImplTestAvailability {
    AgendaDAO agendaDAO = new AgendaDAOImpl();


    @BeforeAll
    static void populate() throws IOException {

        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Availability prova = new Availability("2020-10-17", 9);
        Availability prova2 = new Availability("2022-10-22", 10);
        Availability prova3 = new Availability("2020-10-18", 11);
        assertTrue(agendaDAO.insertAvailability(prova));
        assertTrue(agendaDAO.insertAvailability(prova2));
        assertTrue(agendaDAO.insertAvailability(prova3));
    }

    @Test
    void findAllByDateTest() throws InterruptedException, ExecutionException, IOException {
        assertNotNull(agendaDAO.findAvailabilityByDate("2020-10-17"));
        assertSame(ArrayList.class, agendaDAO.findAvailabilityByDate("2020-10-17").getClass());
        assertNotEquals(0, agendaDAO.findAvailabilityByDate("2020-10-17").size());
    }

    @Test
    void findAllByDateAndTimeTest() throws InterruptedException, ExecutionException, IOException {
        assertNotNull(agendaDAO.findAvailabilityByDateAndTime("2020-10-17", 9));
        assertSame(Availability.class, agendaDAO.findAvailabilityByDateAndTime("2020-10-17", 9).getClass());
        assertNotEquals(new Availability(), agendaDAO.findAvailabilityByDateAndTime("2020-10-17", 9));
    }


    @AfterAll
    static void spopola() throws InterruptedException, ExecutionException, IOException {
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Availability prova = new Availability("2020-10-17", 9);
        Availability prova2 = new Availability("2022-10-22", 10);
        Availability prova3 = new Availability("2020-10-18", 11);
        assertTrue(agendaDAO.deleteAvailability(prova));
        assertTrue(agendaDAO.deleteAvailability(prova2));
        assertTrue(agendaDAO.deleteAvailability(prova3));
    }
}