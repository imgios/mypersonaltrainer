package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.api.Http;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class AllAppointmentsServletTest {

    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response= Mockito.mock(HttpServletResponse.class);
    PrintWriter out= Mockito.mock(PrintWriter.class);

    @BeforeAll
    static void setUp() throws IOException {
        Appointment appointment=new Appointment("2021-09-25","12","prova@gmail.com");
        Appointment appointment2=new Appointment("2021-09-25","15","prova2@gmail.com");
        Appointment appointment3=new Appointment("2021-09-25","16","prova3@gmail.com");
        AgendaDAO dao=new AgendaDAOImpl();
        dao.saveAppointment(appointment3);
        dao.saveAppointment(appointment);
        dao.saveAppointment(appointment2);
    }


    @Test
    void doPostnulllist() throws IOException, ServletException {
        Mockito.when(request.getParameter("data")).thenReturn("2120-09-25");
        Mockito.when(response.getWriter()).thenReturn(out);
        new AllAppointmentsServlet().doPost(request,response);

    }


    @Test
    void doPost() throws IOException, ServletException {
        Mockito.when(request.getParameter("data")).thenReturn("2021-09-25");
        Mockito.when(response.getWriter()).thenReturn(out);
        new AllAppointmentsServlet().doPost(request,response);

    }

    @Test
    void doGet() throws IOException, ServletException {
        Mockito.when(request.getParameter("data")).thenReturn("2021-09-25");
        Mockito.when(response.getWriter()).thenReturn(out);
        new AllAppointmentsServlet().doGet(request,response);
    }

    @AfterAll
    static void Pulisci() throws InterruptedException, ExecutionException, IOException {
        Appointment appointment=new Appointment("2021-09-25","12","prova@gmail.com");
        Appointment appointment2=new Appointment("2021-09-25","15","prova2@gmail.com");
        Appointment appointment3=new Appointment("2021-09-25","16","prova3@gmail.com");
        AgendaDAO dao=new AgendaDAOImpl();
        dao.deleteappointment(appointment);
        dao.deleteappointment(appointment2);
        dao.deleteappointment(appointment3);

    }
}