package it.unisa.c03.myPersonalTrainer.agenda.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaServiceImplTest {
    AgendaService agendaService = new AgendaServiceImpl();

    @Test
    void checkAvailabilityDataFormatNotValid()throws IllegalArgumentException {
        String data = "2020-10-5x";
        String time = "10";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           agendaService.checkAvailability(data,time);
        });
        assertEquals("Formato data non valido", exception.getMessage());
    }

    @Test
    void checkAvailabilityPreviousDate()throws IllegalArgumentException {
        String data = "2000-10-05";
        String time = "10";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           agendaService.checkAvailability(data,time);
        });
        assertEquals("Data precedente a quella odierna", exception.getMessage());
    }

    @Test
    void checkAvailabilityTimeFormatNotValid()throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "1x0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           agendaService.checkAvailability(data,time);
        });
        assertEquals("Formato orario non valido", exception.getMessage());
    }

    @Test
    void checkAvailabilityTimeNotValid()throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "80";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           agendaService.checkAvailability(data,time);
        });
        assertEquals("Orario non valido", exception.getMessage());
    }
    @Test
    void checkAvailabilityPass()throws IllegalArgumentException {
        String data = "2021-10-05";
        String time = "15";
        assertTrue(agendaService.checkAvailability(data,time));
    }
}