package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RequestAppointmentServletTest {

    @BeforeAll
    static void setUp() throws IOException, ExecutionException, InterruptedException {
        AgendaDAO dao1=new AgendaDAOImpl();
        Availability test=new Availability("2021-09-23",11);
        dao1.deleteAvailability(test);
        dao1.insertAvailability(test);
        Availability test2=new Availability("2021-09-23",12);
        dao1.insertAvailability(test2);
    }



    AgendaDAO dao=Mockito.mock(AgendaDAO.class);
    AgendaService service= Mockito.mock(AgendaService.class);
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
    Availability availability=Mockito.mock(Availability.class);
    HttpSession session=Mockito.mock(HttpSession.class);
    PrintWriter writer= Mockito.mock(PrintWriter.class);


    @Test
    void doPost() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(availability.getDate()).thenReturn("2021-09-23");
        Mockito.when(request.getParameter("data")).thenReturn("2021-09-23");
        assertEquals("2021-09-23",request.getParameter("data"));
        Mockito.when(dao.deleteAvailability(any())).thenReturn(true);
        Mockito.when(request.getParameter("time")).thenReturn("11");
        Mockito.when(request.getParameter("mailutente")).thenReturn("prova@gmail.com");
        Mockito.when(service.createAppointment(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        assertTrue(service.createAppointment(request.getParameter("data"),request.getParameter("time"),request.getParameter("mailutente")));
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new RequestAppointmentServlet().doPost(request,response);
    }

    @Test
    void doGet() throws InterruptedException, ExecutionException, IOException, ServletException {
        Mockito.when(availability.getDate()).thenReturn("2021-09-23");
        Mockito.when(request.getParameter("data")).thenReturn("2021-09-23");
        assertEquals("2021-09-23",request.getParameter("data"));
        Mockito.when(dao.deleteAvailability(any())).thenReturn(true);
        Mockito.when(request.getParameter("time")).thenReturn("12");
        Mockito.when(request.getParameter("mailutente")).thenReturn("prova@gmail.com");
        Mockito.when(service.createAppointment(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        assertTrue(service.createAppointment(request.getParameter("data"),request.getParameter("time"),request.getParameter("mailutente")));
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new RequestAppointmentServlet().doGet(request,response);
    }

    @AfterAll
    static void pulisci() throws InterruptedException, ExecutionException, IOException {
        AgendaDAO dao1=new AgendaDAOImpl();
        Appointment appointment=new Appointment("2021-09-23","11","prova@gmail.com");
        dao1.deleteappointment(appointment);
        Appointment appointment2=new Appointment("2021-09-23","12","prova@gmail.com");
        dao1.deleteappointment(appointment2);
    }

}