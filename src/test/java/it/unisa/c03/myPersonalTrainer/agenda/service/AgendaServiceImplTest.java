package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class AgendaServiceImplTest {
    AgendaDAO dao= Mockito.mock(AgendaDAO.class);

    @Test
    void failcreateAppointment() throws InterruptedException, ExecutionException, IOException {
        AgendaService service=new AgendaServiceImpl(dao);
        assertFalse(service.createAppointment("2021-12-22","21","prova@gmail.com"));
        assertFalse(service.createAppointment("2021-12-22","7","prova@gmail.com"));
        assertFalse(service.createAppointment("2021-12-22","13","prova@gmail.com"));
        assertFalse(service.createAppointment("2021-12-22","13","provagmail.com"));

    }


    @Test
    void createAppointment() throws IOException, ExecutionException, InterruptedException {
        AgendaService service=new AgendaServiceImpl(dao);
        Mockito.when(dao.saveAppointment(any())).thenReturn(true);
        assertTrue(service.createAppointment("2022-06-18","17","prova@gmail.com"));
        assertFalse(service.createAppointment("2019-12-22","17","prova@gmail.com"));
    }

    @Test
    void removeAppointment() throws InterruptedException, ExecutionException, IOException {
        Appointment app=new Appointment("2022-06-18","17","prova@gmail.com");
        AgendaService service=new AgendaServiceImpl(dao);
        Mockito.when(dao.deleteappointment(any())).thenReturn(true);
        assertTrue(service.removeAppointment(app));
    }

    @Test
    void findAppointmentByDate() throws InterruptedException, ExecutionException, IOException {
        AgendaService service= new AgendaServiceImpl(dao);
        List<Appointment> list = new ArrayList<>();
        Appointment app=new Appointment("2022-06-18","17","prova@gmail.com");
        Appointment app2=new Appointment("2022-06-18","15","prova@gmail.com");
        list.add(app);
        list.add(app2);
        Mockito.when(dao.findAppointmentByDate(anyString())).thenReturn(list);
        assertEquals(list,service.findAppointmentByDate("2022-06-18"));
        Mockito.when(dao.findAppointmentByDate("2019-06-18")).thenReturn(null);
        assertNull(service.findAppointmentByDate("2019-06-18"));
    }
}