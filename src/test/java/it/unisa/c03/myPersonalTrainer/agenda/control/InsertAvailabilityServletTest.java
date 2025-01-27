package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class InsertAvailabilityServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    AgendaDAO agendaDAO = Mockito.mock(AgendaDAO.class);
    AgendaService agendaService = Mockito.mock(AgendaService.class);



    @Test
    void doPost() throws IOException, ExecutionException, InterruptedException {
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        agendaDAO.deleteAvailability(new Availability("2021-10-10", 18));
        agendaDAO.deleteAvailability(new Availability("2021-10-10", 18));
        Mockito.when(request.getParameter("dataSelected")).thenReturn("2021-10-10");
        Mockito.when(request.getParameter("timeSelected")).thenReturn("18");
        Mockito.when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(true);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doPost(request, response);
        assertEquals("1", stringWriter.toString());
        agendaDAO.deleteAvailability(new Availability("2021-10-10", 18));

    }

    /*@Test
    void doGet() throws IOException, ExecutionException, InterruptedException {
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        agendaDAO.deleteAvailability(new Availability("2021-10-10", 18));
        Mockito.when(request.getParameter("dataSelected")).thenReturn("2021-10-10");
        Mockito.when(request.getParameter("timeSelected")).thenReturn("18");
        Mockito.when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(true);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doGet(request, response);
        assertEquals("1", stringWriter.toString());
        agendaDAO.deleteAvailability(new Availability("2021-10-10", 18));

    }*/


    @Test
    void doGetDateAleradyExists() throws IOException, ExecutionException, InterruptedException {
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Mockito.when(request.getParameter("dataSelected")).thenReturn("2121-01-08");
        Mockito.when(request.getParameter("timeSelected")).thenReturn("15");
        Mockito.when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(true);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doGet(request, response);
        assertEquals("2", stringWriter.toString());
    }


    @Test
    void doPostDateAleradyExists() throws IOException, ExecutionException, InterruptedException {
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Mockito.when(request.getParameter("dataSelected")).thenReturn("2121-01-08");
        Mockito.when(request.getParameter("timeSelected")).thenReturn("15");
        Mockito.when(agendaService.checkAvailability(anyString(), any())).thenReturn(true);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(true);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doPost(request, response);
        assertEquals("2", stringWriter.toString());
    }

    @Test
    void doGetNotPass() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2019-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(false);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(false);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doGet(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }

    @Test
    void doPotNotPass() throws IOException {

        when(request.getParameter("dataSelected")).thenReturn("2019-10-10");
        when(request.getParameter("timeSelected")).thenReturn("18");
        when(agendaService.checkAvailability(anyString(), any())).thenReturn(false);
        Mockito.when(agendaService.createAvailability(any())).thenReturn(false);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new InsertAvailabilityServlet().doPost(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }
}