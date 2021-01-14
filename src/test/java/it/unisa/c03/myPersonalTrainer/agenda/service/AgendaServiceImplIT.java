package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaServiceImplIT
{
    AgendaDAO dao= new AgendaDAOImpl();

    @BeforeAll
    static void popola() throws IOException {
        AgendaDAO dao1=new AgendaDAOImpl();
        Appointment app=new Appointment("2022-07-18","17","prova@gmail.com");
        Appointment app2=new Appointment("2022-07-18","15","prova@gmail.com");
        dao1.saveAppointment(app);
        dao1.saveAppointment(app2);
        Availability ava=new Availability("2021-12-22",21);
        Availability ava2=new Availability("2021-12-22",7);
        Availability ava4=new Availability("2021-12-22",13);
        Availability ava5=new Availability("2022-06-18",17);
        dao1.insertAvailability(ava);
        dao1.insertAvailability(ava2);
        dao1.insertAvailability(ava4);
        dao1.insertAvailability(ava5);
    }

    @Test
    void failcreateAppointment() throws InterruptedException, ExecutionException, IOException {
        AgendaService service=new AgendaServiceImpl(dao);
        assertFalse(service.createAppointment("2021-12-22","21","prova@gmail.com"));
        assertFalse(service.createAppointment("2021-12-22","7","prova@gmail.com"));
        assertFalse(service.createAppointment("2021-12-22","13","provagmail.com"));

    }

    @Test
    void createAppointment() throws IOException, ExecutionException, InterruptedException {
        AgendaService service=new AgendaServiceImpl(dao);
        assertTrue(service.createAppointment("2022-06-18","17","prova@gmail.com"));
        assertFalse(service.createAppointment("2019-12-22","17","prova@gmail.com"));
    }

    @Test
    void removeAppointment() throws InterruptedException, ExecutionException, IOException {
        Appointment app=new Appointment("2022-06-18","17","prova@gmail.com");
        AgendaService service=new AgendaServiceImpl(dao);
        assertTrue(service.removeAppointment(app));
    }


    @Test
    void findAppointmentByDate() throws InterruptedException, ExecutionException, IOException {
        AgendaService service= new AgendaServiceImpl(dao);
        List<Appointment> list = new ArrayList<>();
        Appointment app=new Appointment("2022-07-18","17","prova@gmail.com");
        Appointment appt=new Appointment("2022-07-18","14","prova@gmail.com");
        Appointment app2=new Appointment("2022-07-18","15","prova@gmail.com");
        dao.deleteappointment(appt);
        list.add(app);
        list.add(app2);
        List<Appointment> listapresi = service.findAppointmentByDate("2022-07-18");
        assertEquals(list.size(),listapresi.size());
        assertNull(service.findAppointmentByDate("2019-06-18"));
    }

    @Test
    void dateformatnotvalid()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="x23-";
        String time="12";
        assertThrows(IllegalArgumentException.class,()->service.checkAvailability(data,time));
    }
    @Test
    void timeformatnotvalid()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="2120-02-03";
        String time="xx";
        assertThrows(IllegalArgumentException.class,()->service.checkAvailability(data,time));
    }

    @Test
    void datebeforetoday()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="1920-02-03";
        String time="xx";
        assertThrows(IllegalArgumentException.class,()->service.checkAvailability(data,time));
    }

    @Test
    void timeminusMin()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="2120-02-03";
        String time="6";
        assertThrows(IllegalArgumentException.class,()->service.checkAvailability(data,time));
    }

    @Test
    void timemorethanMAX()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="2120-02-03";
        String time="22";
        assertThrows(IllegalArgumentException.class,()->service.checkAvailability(data,time));
    }

    @Test
    void goodavailability()
    {
        AgendaDAO dao=new AgendaDAOImpl();
        AgendaService service=new AgendaServiceImpl(dao);
        String data="2120-02-03";
        String time="17";
        assertTrue(service.checkAvailability(data,time));
    }




    @AfterAll
    static void pulisci() throws InterruptedException, ExecutionException, IOException {
        AgendaDAO dao1=new AgendaDAOImpl();

        /*clean createAppointment */
        Appointment a=new Appointment("2022-06-18","17","prova@gmail.com");
        dao1.deleteappointment(a);

        /*clean findappointmentbydate*/
        Appointment app=new Appointment("2022-07-18","17","prova@gmail.com");
        Appointment app2=new Appointment("2022-07-18","15","prova@gmail.com");
        dao1.deleteappointment(app);
        dao1.deleteappointment(app2);

        /*clean availability*/
        Availability ava=new Availability("2021-12-22",21);
        Availability ava2=new Availability("2021-12-22",7);
        Availability ava3=new Availability("2021-12-22",18);
        Availability ava4=new Availability("2021-12-22",13);
        dao1.deleteAvailability(ava);
        dao1.deleteAvailability(ava2);
        dao1.deleteAvailability(ava3);
        dao1.deleteAvailability(ava4);
    }
}
