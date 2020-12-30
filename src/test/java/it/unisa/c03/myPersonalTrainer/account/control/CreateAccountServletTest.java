package it.unisa.c03.myPersonalTrainer.account.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateAccountServletTest {

  HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);


  @Test
  void doPostPass() throws IOException, ServletException, ExecutionException, InterruptedException {
   Mockito.when(request.getParameter("username")).thenReturn("TestUsername");
   assertEquals("TestUsername", request.getParameter("username"));
   Mockito.when(request.getParameter("surname")).thenReturn("TestSurname");
   Mockito.when(request.getParameter("phone")).thenReturn("0000000000");
   Mockito.when(request.getParameter("email")).thenReturn("test@test.it");
   Mockito.when(request.getParameter("password")).thenReturn("Test001");
   Mockito.when(request.getParameter("role")).thenReturn("0");
    Account user = new Account (request.getParameter("username"),request.getParameter("surname"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("passowrd"), request.getIntHeader("role"));

    Mockito.when(accountService.registerAccount(Mockito.any())).thenReturn(true);

      assertTrue(accountService.registerAccount(user));
     new CreateAccountServlet().doPost(request,response);

  }


  @Test
  void doGet() {
  }
}