package it.unisa.c03.myPersonalTrainer;

import it.unisa.c03.myPersonalTrainer.Account.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.Account.bean.Account;
import it.unisa.c03.myPersonalTrainer.Account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.Account.dao.AccountDAOImpl;

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

        String presa = request.getParameter("prova");

        System.out.println(presa);

        //Account a = new Account("mic", "mic","cliente@gmail.com","ciaociao1.",1);

        //DBConnection.getConnection().collection("Account").add(a);
        AccountDAO acc = new AccountDAOImpl();
        Account prova = new Account();

        prova = acc.findAccountByEmail("ciao@a.it");

        System.out.println("ECCOOOLOOOOOOOOO:" + prova.toString());

    }

    public void destroy() {
    }
}