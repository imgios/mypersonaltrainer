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

class ChangePasswordControllerTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
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

        new ChangePasswordController().doPost(request, response);

    }

    @Test
    void doPostFalse() throws ServletException, IOException {
        Mockito.when(request.getParameter("email")).thenReturn("cliente@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");

        //Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenReturn(false);

        Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.checkCredentials("prova@gmail.com","password3");
        } );

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);


        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());






        new ChangePasswordController().doPost(request, response);
    }

    @Test
    void doGet() throws ServletException, IOException {
        //new ChangePasswordController().doGet(request, response);

        Mockito.when(request.getParameter("email")).thenReturn("cliente@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("nuovaPassword1");

        Mockito.when(accountService.checkCredentials(anyString(),anyString())).thenReturn(true);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);


        new ChangePasswordController().doPost(request, response);
    }
}