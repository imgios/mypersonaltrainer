package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class HoursServletTest {
    @BeforeAll
    static void setUp() throws IOException {
        AgendaDAO dao1=new AgendaDAOImpl();
        Availability test=new Availability("2021-09-23",11);
        dao1.insertAvailability(test);
        Availability test2=new Availability("2021-09-23",12);
        dao1.insertAvailability(test2);

    }


    AgendaDAO dao= Mockito.mock(AgendaDAO.class);
    AgendaService service= Mockito.mock(AgendaService.class);
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response=Mockito.mock(HttpServletResponse.class);
    Availability availability=Mockito.mock(Availability.class);
    HttpSession session=Mockito.mock(HttpSession.class);
    PrintWriter writer= Mockito.mock(PrintWriter.class);

    @Test
    void doPost() throws IOException, ExecutionException, InterruptedException, ServletException {
        Availability test=new Availability("2021-09-23",11);
        Availability test2=new Availability("2021-09-23",12);
        List<Availability> list= new ArrayList<>();
        list.add(test);
        list.add(test2);
        Mockito.when(request.getParameter("dataappuntamento")).thenReturn("2021-09-23");
        assertEquals("2021-09-23",request.getParameter("dataappuntamento"));
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(service.getAvailabilityByDate(Mockito.anyString())).thenReturn(list);
        Mockito.doNothing().when(response).setContentType(Mockito.anyString());
        Mockito.doNothing().when(response).setCharacterEncoding(Mockito.anyString());
        Mockito.doNothing().when(writer).print(Mockito.anyChar());
        Mockito.doNothing().when(writer).flush();
        new HoursServlet().doPost(request,response);
    }

    @Test
    void doGet() throws IOException, ServletException {
        Mockito.when(request.getParameter("dataappuntamento")).thenReturn("2021-09-23");
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.doNothing().when(writer).print(Mockito.anyChar());
        Mockito.doNothing().when(writer).flush();
        Mockito.doNothing().when(response).setContentType(Mockito.anyString());
        Mockito.doNothing().when(response).setCharacterEncoding(Mockito.anyString());
        new HoursServlet().doGet(request,response);
    }

    @AfterAll
    static void pulisci() throws InterruptedException, ExecutionException, IOException {
        AgendaDAO dao1=new AgendaDAOImpl();
        Availability test=new Availability("2021-09-23",11);
        dao1.deleteAvailability(test);
        Availability test2=new Availability("2021-09-23",12);
        dao1.deleteAvailability(test2);

    }
}