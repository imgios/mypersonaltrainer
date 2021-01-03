package it.unisa.c03.myPersonalTrainer.agenda.dao;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
class AgendaDAOImplTest {

    AgendaDAOImpl dao=new AgendaDAOImpl();

    @BeforeAll
     static void populate() throws IOException {
        AgendaDAOImpl dao=new AgendaDAOImpl();

        Appointment prova=new Appointment("2020-05-17","17","gigio@gmail.com");
        Appointment provabis=new Appointment("2020-06-15","11","gigio@gmail.com");
        Appointment prova2=new Appointment("2022-03-22","12","gigioprova@gmail.com");
        Appointment prova3=new Appointment("2020-05-17","16","gigioarola@gmail.com");
        assertTrue(dao.saveAppointment(prova));
        assertTrue(dao.saveAppointment(provabis));
        assertTrue(dao.saveAppointment(prova2));
        assertTrue(dao.saveAppointment(prova3));


    }

    @Test
    void findAppointmetsByEmail() throws InterruptedException, ExecutionException, IOException {
        assertNotNull(dao.findAppointmetsByEmail("gigio@gmail.com"));
        assertSame(ArrayList.class,dao.findAppointmetsByEmail("gigio@gmail.com").getClass());
        assertNotEquals(0,dao.findAppointmetsByEmail("gigio@gmail.com").size());
    }

    @Test
    void findAppointmentByDate() throws InterruptedException, ExecutionException, IOException {
        assertNotNull(dao.findAppointmentByDate("2020-05-17"));
        assertSame(ArrayList.class, dao.findAppointmentByDate("2020-05-17").getClass());
        assertNotEquals(0, dao.findAppointmentByDate("2020-05-17").size());
    }

    @AfterAll
    static void spopola() throws InterruptedException, ExecutionException, IOException {
        AgendaDAOImpl dao=new AgendaDAOImpl();

        Appointment prova=new Appointment("2020-05-17","17","gigio@gmail.com");
        Appointment provabis=new Appointment("2020-06-15","11","gigio@gmail.com");
        Appointment prova2=new Appointment("2022-03-22","12","gigioprova@gmail.com");
        Appointment prova3=new Appointment("2020-05-17","16","gigioarola@gmail.com");
        assertTrue(dao.deleteappointment(prova));
        assertTrue(dao.deleteappointment(provabis));
        assertTrue(dao.deleteappointment(prova2));
        assertTrue(dao.deleteappointment(prova3));

    }
}