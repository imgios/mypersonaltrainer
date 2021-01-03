package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RemoveAppointmentServletTest {
    AgendaService service= Mockito.mock(AgendaService.class);
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
    PrintWriter print=Mockito.mock(PrintWriter.class);
    @Test
    void doPost() throws InterruptedException, ExecutionException, IOException, ServletException {

        Mockito.when(request.getParameter("dataapp")).thenReturn("2021-10-10");
        assertEquals("2021-10-10",request.getParameter("dataapp"));
        Mockito.when(request.getParameter("ora")).thenReturn("15");
        Mockito.when(request.getParameter("mail")).thenReturn("prova@gmail.com");
        Mockito.when(service.removeAppointment(any())).thenReturn(true);
        Mockito.when(response.getWriter()).thenReturn(print);
        new RemoveAppointmentServlet().doPost(request,response);
        new RemoveAppointmentServlet().doGet(request,response);
    }

    @Test
    void doGet() {
    }
}