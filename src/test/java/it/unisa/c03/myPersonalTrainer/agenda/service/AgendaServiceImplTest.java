package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AgendaServiceImplTest {
    AgendaDAO dao= Mockito.mock(AgendaDAO.class);

    @Test
    void checkDate() {

    }

    @Test
    void createAppointment() throws IOException {
        AgendaService service=new AgendaServiceImpl(dao);
        Mockito.when(dao.saveAppointment(any())).thenReturn(true);
        assertTrue(service.createAppointment("2022-06-18","17","prova@gmail.com"));
    }

    @Test
    void removeAppointment() {
    }

    @Test
    void findAppointmentByDate() {
    }
}