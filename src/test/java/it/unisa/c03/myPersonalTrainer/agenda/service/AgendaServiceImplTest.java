package it.unisa.c03.myPersonalTrainer.agenda.service;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

class AgendaServiceImplTest {
    AgendaDAO agendaDAO = Mockito.mock(AgendaDAO.class);

    AgendaService agendaService = new AgendaServiceImpl(agendaDAO);

    @Test
    void checkAvailabilityDataFormatNotValid() throws IllegalArgumentException {
        String data = "2020-10-5x";
        String time = "10";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            agendaService.checkAvailability(data, time);
        });
        assertEquals("Formato data non valido", exception.getMessage());
    }

    @Test
    void checkAvailabilityPreviousDate() throws IllegalArgumentException {
        String data = "2000-10-05";
        String time = "10";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            agendaService.checkAvailability(data, time);
        });
        assertEquals("Data precedente a quella odierna", exception.getMessage());
    }

    @Test
    void checkAvailabilityTimeFormatNotValid() throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "1x0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            agendaService.checkAvailability(data, time);
        });
        assertEquals("Formato orario non valido", exception.getMessage());
    }

    @Test
    void checkAvailabilityTimeNotValid() throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "80";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            agendaService.checkAvailability(data, time);
        });
        assertEquals("Orario non valido", exception.getMessage());
    }

    @Test
    void checkAvailabilityPass() throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "15";
        assertTrue(agendaService.checkAvailability(data, time));
    }


    @Test
    void createAvailability() throws IOException {
        Mockito.when(agendaDAO.insertAvailability(any())).thenReturn(true);
        assertEquals(true, agendaService.createAvailability(any()));
    }

    @Test
    void getAvailabilityByDate() throws InterruptedException, ExecutionException, IOException {

        Availability prova = new Availability("2020-10-17", 9);
        Availability prova2 = new Availability("2022-10-22", 10);
        List<Availability> list = new ArrayList<>();
        list.add(prova);
        list.add(prova2);
        Mockito.when(agendaDAO.findAvailabilityByDate(any())).thenReturn(list);
        assertEquals(list, agendaService.getAvailabilityByDate(any()));
    }

    @Test
    void removeAvailability() throws InterruptedException, ExecutionException, IOException {

        Mockito.when(agendaDAO.deleteAvailability(any())).thenReturn(true);
        assertEquals(true, agendaService.removeAvailability(any()));
    }

    @Test
    void getAvailabilityByDateAndTime() throws InterruptedException, ExecutionException, IOException {
        Availability prova = new Availability("2020-10-17", 9);
        Mockito.when(agendaDAO.findAvailabilityByDateAndTime(any(), anyInt())).thenReturn(prova);
        assertEquals(prova, agendaService.getAvailabilityByDateAndTime(any(), anyInt()));
    }
}