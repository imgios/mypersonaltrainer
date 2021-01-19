package it.unisa.c03.myPersonalTrainer.parameters.control;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;
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
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ParametersServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    ParametersServiceImpl parametersService = Mockito.mock(ParametersServiceImpl.class);

    @BeforeAll
    static void befinserttp() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("Parameters").whereEqualTo("mailClient","parametri@test.it").get().get().getDocuments();
        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }


    @Test
    void doPostPass() throws IOException, ServletException {

        Parameters a = new Parameters(100, 20, 30, "parametri@test.it");
        when(request.getParameter("weight")).thenReturn("50");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("clienteMail")).thenReturn("parametri@test.it");

        when(parametersService.createParameters(
                "50", "20%", "25%","parametri@test.it")).thenReturn(a);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersServlet().doPost(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doGetPass() throws IOException, ServletException {
        Parameters a = new Parameters(100, 20, 30, "parametri@test.it");

        when(request.getParameter("weight")).thenReturn("50");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");

        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("clienteMail")).thenReturn("parametri@test.it");
        when(parametersService.createParameters(
                "50", "20%", "25%","parametri@test.it")).thenReturn(a);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersServlet().doGet(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doPostNotPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50A");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("clienteMail")).thenReturn("parametri@test.it");
        when(parametersService.createParameters(
                "50", "20%", "25%","parametri@test.it")).thenThrow(new NumberFormatException(
                "formato peso non valido"));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersServlet().doPost(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }

    @Test
    void doGetNotPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50A");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session
                .getAttribute("clienteMail")).thenReturn("parametri@test.it");
        when(parametersService.createParameters(
                "50", "20%", "25%","parametri@test.it")).thenThrow(new NumberFormatException(
                "formato peso non valido"));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersServlet().doGet(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }



    @AfterAll
    static void afterinserttp() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("Parameters").whereEqualTo("mailClient","parametri@test.it").get().get().getDocuments();
        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

}
