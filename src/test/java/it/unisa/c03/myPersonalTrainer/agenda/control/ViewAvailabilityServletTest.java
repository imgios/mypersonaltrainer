package it.unisa.c03.myPersonalTrainer.agenda.control;

import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.control.ParametersController;
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

class ViewAvailabilityServletTest {


    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AgendaServiceImpl agendaService = Mockito.mock(AgendaServiceImpl.class);

    @Test
    void doPostPass() throws IOException, ServletException {

        when(request.getParameter("dataSelected")).thenReturn("2020-12-30");

        /*when(agendaService.createParameters(
                "50", "20%", "25%")).thenReturn(new Parameters());
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new ParametersController().doPost(request, response);
        assertEquals("1", stringWriter.toString());*/
    }


}