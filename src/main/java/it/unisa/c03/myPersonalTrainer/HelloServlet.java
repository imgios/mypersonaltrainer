package it.unisa.c03.myPersonalTrainer;


import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Account a = new Account("mic", "mic","cliente@gmail.com","ciaociao1.",1);
        //DBConnection.getConnection().collection("Account").add(a);

        Account prova = new Account();
        AccountDAO acc = new AccountDAOImpl();

         prova = acc.findAccountByEmail("prova@prova.it");

         if (prova.getEmail() != null)
             System.out.println("Trovato");
         else if (prova.getEmail() == null)
             System.out.println("Non Trovato");


        // acc.updatePassword("prova@prova.it","nuovapasscambia23.");

    }

    public void destroy() {
    }
}