package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityControllerTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AgendaServiceImpl agendaService = Mockito.mock(AgendaServiceImpl.class);

    @Test
    void doPost() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2021-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        doNothing().when(agendaService).createAvailability(any());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AvailabilityController().doPost(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doGet() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2021-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        doNothing().when(agendaService).createAvailability(any());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AvailabilityController().doGet(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doGetNotPass() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2019-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(false);
        doNothing().when(agendaService).createAvailability(any());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AvailabilityController().doGet(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }

    @Test
    void doPotNotPass() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2019-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(false);
        doNothing().when(agendaService).createAvailability(any());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AvailabilityController().doPost(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }
}