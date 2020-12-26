package it.unisa.c03.myPersonalTrainer.account.control;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( name ="LoginServlet", value= "/LoginServlet")
public class LoginServlet extends HttpServlet {

    AccountService service = new AccountServiceImpl();

    //private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

       AccountServiceImpl accountTry;
        accountTry = new AccountServiceImpl();

        accountTry.checkCredentials(email, password);
        if(accountTry.checkCredentials(email, password))
        {
            if(accountTry.loginAccount(email, password))
            {
                System.out.println("Bentornato!");
                response.sendRedirect("clienteDashboard.jsp");
            }
            else
            {
                System.out.println("Credenziali inesistenti!");
                response.sendRedirect("login.jsp");
            }
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
