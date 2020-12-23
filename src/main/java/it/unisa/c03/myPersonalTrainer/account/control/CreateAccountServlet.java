package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;


@WebServlet(name = "Registrazione", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //valori passati dalla form jsp
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role = 0;

        //creazione nuovo oggetto

        Account utente = new Account(name, surname, phone, email, password, role);

        //System.out.println("---------------------------");
        //System.out.println(utente);

        AccountService accountService = new AccountServiceImpl();
        accountService.RegisterAccount(utente);

        //AccountDAO accountDAO = new AccountDAOImpl();
        //accountDAO.saveAccount(utente);


        //Hello----stampa messaggio di funzionamento
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>ciao, guarda il log</h1>");
        out.println("</body></html>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
