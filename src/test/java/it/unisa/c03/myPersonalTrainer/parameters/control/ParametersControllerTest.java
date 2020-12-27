package it.unisa.c03.myPersonalTrainer.parameters.control;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ParametersControllerTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    ParametersServiceImpl parametersService = Mockito.mock(ParametersServiceImpl.class);

    @Test
    void doPostPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        when(parametersService.createParameters(
                "50", "20%", "25%")).thenReturn(new Parameters());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersController().doPost(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doGetPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        when(parametersService.createParameters(
                "50", "20%", "25%")).thenReturn(new Parameters());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersController().doGet(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    void doPostNotPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50A");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        when(parametersService.createParameters(
                "50", "20%", "25%")).thenThrow(new NumberFormatException(
                "formato peso non valido"));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersController().doPost(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }

    @Test
    void doGetNotPass() throws IOException, ServletException {

        when(request.getParameter("weight")).thenReturn("50A");
        when(request.getParameter("leanMass")).thenReturn("20%");
        when(request.getParameter("fatMass")).thenReturn("25%");
        when(parametersService.createParameters(
                "50", "20%", "25%")).thenThrow(new NumberFormatException(
                "formato peso non valido"));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersController().doGet(request, response);
        assertFalse(stringWriter.toString().contains("1"));
    }
}