package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.api.client.json.Json;
import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.control.ParametersController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ViewAvailabilityServletTest {


    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AgendaService agendaService = Mockito.mock(AgendaService.class);

    @Test
    void doPostPass0() throws IOException, ServletException, ExecutionException, InterruptedException {

        ArrayList<Availability> list = new ArrayList<Availability>();

        Mockito.when(request.getParameter("dataSelected")).thenReturn("2021-12-30");
        Mockito.when(agendaService.checkAvailability("2021-12-20", "15")).thenReturn(true);
        Mockito.when(agendaService.getAvailabilityByDate("2021-12-20")).thenReturn(list);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ViewAvailabilityServlet().doPost(request, response);
        assertEquals("0", stringWriter.toString());
    }


    //testo che non mi da 0 (non ci sono disponibilita) e ne che mi da un array(ci sono disponibilita)
    @Test
    void doPost1() throws IOException, ServletException, ExecutionException, InterruptedException {

        ArrayList<Availability> list = new ArrayList<Availability>();

        Mockito.when(request.getParameter("dataSelected")).thenReturn("2020-12-30");
        Mockito.when(agendaService.checkAvailability("2020-12-30", "15")).thenReturn(false);
        Mockito.when(agendaService.getAvailabilityByDate("2020-12-20")).thenReturn(list);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ViewAvailabilityServlet().doPost(request, response);
        assertNotEquals("0", stringWriter.toString());
        assertNotEquals(list.getClass(), stringWriter.toString());
    }


    //testo che non mi da 0 (non ci sono disponibilita) e ne che mi da un array(ci sono disponibilita)
    @Test
    void doPostList() throws IOException, ServletException, ExecutionException, InterruptedException {

        ArrayList<Availability> list = new ArrayList<Availability>();
        Availability availability = new Availability("2021-12-12", 10);
        list.add(availability);

        Mockito.when(request.getParameter("dataSelected")).thenReturn("2021-12-12");
        Mockito.when(agendaService.checkAvailability("2021-12-12", "10")).thenReturn(true);
        Mockito.when(agendaService.getAvailabilityByDate("2021-12-12")).thenReturn(list);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ViewAvailabilityServlet().doPost(request, response);

        assertNotEquals("0", stringWriter.toString());
        // mi accerto che ho quella stringa , con 1 elemento!
        assertEquals(33, stringWriter.toString().length());
    }


    @Test
    void doGetPass0() throws IOException, ServletException, ExecutionException, InterruptedException {

        ArrayList<Availability> list = new ArrayList<Availability>();

        Mockito.when(request.getParameter("dataSelected")).thenReturn("2021-12-30");
        Mockito.when(agendaService.checkAvailability("2021-12-20", "15")).thenReturn(true);
        Mockito.when(agendaService.getAvailabilityByDate("2021-12-20")).thenReturn(list);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ViewAvailabilityServlet().doGet(request, response);
        assertEquals("0", stringWriter.toString());
    }
}