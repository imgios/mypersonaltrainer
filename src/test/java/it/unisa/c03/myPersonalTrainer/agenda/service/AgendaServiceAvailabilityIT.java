package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class AgendaServiceAvailabilityIT {
    AgendaDAO dao= new AgendaDAOImpl();
   AgendaService agendaService=new AgendaServiceImpl(dao);

   @BeforeAll
   static void insert() throws IOException {
       AgendaDAO dao=new AgendaDAOImpl();
       Availability prova = new Availability("2022-10-17", 9);
       Availability prova2 = new Availability("2022-10-17", 10);
       Availability prova3 = new Availability("2020-10-17", 9);
       dao.insertAvailability(prova);
       dao.insertAvailability(prova2);
       dao.insertAvailability(prova3);
   }


    @Test
    void createAvailability() throws IOException {
        Availability ava=new Availability("2023-01-12",15);
        assertEquals(true, agendaService.createAvailability(ava));
    }

    @Test
    void getAvailabilityByDate() throws InterruptedException, ExecutionException, IOException {

        Availability prova = new Availability("2022-10-17", 9);
        Availability prova2 = new Availability("2022-10-17", 10);
        Availability provat = new Availability("2022-11-17", 10);
        dao.deleteAvailability(provat);
        List<Availability> list = new ArrayList<>();
        list.add(prova);
        list.add(prova2);
        assertEquals(list.size(), agendaService.getAvailabilityByDate("2022-10-17").size());
    }


    @Test
    void removeAvailability() throws InterruptedException, ExecutionException, IOException {
        Availability ava=new Availability("2323-01-12",15);
        assertEquals(true, agendaService.removeAvailability(ava));
    }

    @Test
    void getAvailabilityByDateAndTime() throws InterruptedException, ExecutionException, IOException {
        Availability prova = new Availability("2020-10-17", 9);
        Availability x=agendaService.getAvailabilityByDateAndTime("2020-10-17", 9);
        Availability y=agendaService.getAvailabilityByDateAndTime("2020-10-17", 9);
        assertEquals(prova.getClass(), y.getClass());
    }

    @AfterAll
    static void clean() throws InterruptedException, ExecutionException, IOException {
        Availability ava=new Availability("2023-01-12",15);
        AgendaDAO dao= new AgendaDAOImpl();
        dao.deleteAvailability(ava);

        Availability prova = new Availability("2022-10-17", 9);
        Availability prova2 = new Availability("2022-10-17", 10);
        Availability prova3 = new Availability("2020-10-17", 9);
        dao.deleteAvailability(prova3);
        dao.deleteAvailability(prova);
        dao.deleteAvailability(prova2);
    }
}
