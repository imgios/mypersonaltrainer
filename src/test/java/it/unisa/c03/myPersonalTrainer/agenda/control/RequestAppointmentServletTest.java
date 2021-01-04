package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

class RequestAppointmentServletTest {

    AgendaDAO dao=Mockito.mock(AgendaDAO.class);
    AgendaService service= Mockito.mock(AgendaService.class);
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
    Availability availability=Mockito.mock(Availability.class);
    HttpSession session=Mockito.mock(HttpSession.class);
    @Test
    void doPost() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(availability.getDate()).thenReturn("2021-10-10");
       Mockito.when(request.getParameter("data")).thenReturn("2021-10-10");
        assertEquals("2021-10-10",request.getParameter("data"));
        Mockito.when(dao.deleteAvailability(any())).thenReturn(true);
        Mockito.when(request.getParameter("time")).thenReturn("15");
        Mockito.when(request.getParameter("mailutente")).thenReturn("prova@gmail.com");
        Mockito.when(service.createAppointment(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        assertTrue(service.createAppointment(request.getParameter("data"),request.getParameter("time"),request.getParameter("mailutente")));
        Mockito.when(request.getSession()).thenReturn(session);
new RequestAppointmentServlet().doPost(request,response);
/*
new RequestAppointmentServlet().doGet(request,response);
*/
    }

    @Test
    void doGet() throws ServletException, IOException {


    }
}