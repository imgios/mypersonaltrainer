package it.unisa.c03.myPersonalTrainer.account.control;


import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangePasswordServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);


    @Test
    void doPostCatchIAE() throws ServletException, IOException, ExecutionException, InterruptedException {

        Mockito.when(request.getParameter("email")).thenReturn("cliente@@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");
        Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenReturn(true);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());
        Mockito.when(accountService.searchAccountByEmail(anyString())).thenReturn(true);
        Mockito.when(accountService.changePassword(anyString(),anyString())).thenReturn(true);
        new ChangePasswordServlet().doPost(request, response);

    }

    @Test
    void doPostTrue() throws ServletException, IOException, ExecutionException, InterruptedException {

        Mockito.when(request.getParameter("email")).thenReturn("cliente@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");

        Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenReturn(true);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(accountService.searchAccountByEmail(anyString())).thenReturn(true);
        Mockito.when(accountService.changePassword(anyString(),anyString())).thenReturn(true);
        new ChangePasswordServlet().doPost(request, response);

    }

    @Test
    void doPostFalse() throws ServletException, IOException {
        Mockito.when(request.getParameter("email")).thenReturn("cliente@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");
        Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.checkCredentials("prova@gmail.com","password3");
        } );

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        new ChangePasswordServlet().doPost(request, response);
    }

    @Test
    void doPostSession() throws ServletException, IOException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn("cliente@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("clienteMail")).thenReturn("cliente@gmail.com");
        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());
        new ChangePasswordServlet().doPost(request, response);

    }

    @Test
    void doPostSessionfalse() throws ServletException, IOException {
        Mockito.when(request.getParameter("email")).thenReturn("cliente2@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("clienteMail")).thenReturn("cliente@gmail.com");
        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());
        new ChangePasswordServlet().doPost(request, response);
    }

    @Test
    void dopostExistmail() throws ServletException, IOException {
        Mockito.when(request.getParameter("email")).thenReturn("umbertofranzes@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("mypt2021");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        new ChangePasswordServlet().doGet(request, response);
    }

    @Test
    void dopostExistmailinsession() throws ServletException, IOException {
        Mockito.when(request.getParameter("email")).thenReturn("umbertofranzes@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("mypt2021");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn("umbertofranzes@gmail.com");
        new ChangePasswordServlet().doGet(request, response);
    }

}
