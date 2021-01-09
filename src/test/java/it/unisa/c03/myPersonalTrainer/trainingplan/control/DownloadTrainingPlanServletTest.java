package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class DownloadTrainingPlanServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Test
    void prova() throws DocumentException, IOException {



        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session.getAttribute("date")).thenReturn("2021-10-10");
        Mockito.when(session.getAttribute("exerc")).thenReturn("somestring");


        ServletOutputStream out = Mockito.mock(ServletOutputStream.class);
        Mockito.when(response.getOutputStream()).thenReturn(out);


        Document x = Mockito.mock(Document.class);
        Mockito.when(x.add(any())).thenThrow(DocumentException.class);



        //assertNotEquals(DocumentException.class, () -> new downloadTrainingPlanServlet(x).doPost(request, response));
    }
}