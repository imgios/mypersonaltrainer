package it.unisa.c03.myPersonalTrainer.control.servlet;

import it.unisa.c03.myPersonalTrainer.control.service.AccountService;
import it.unisa.c03.myPersonalTrainer.control.serviceImpl.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.model.bean.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {

    AccountService service = new AccountServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get parameters form the request form
        String clientMail = request.getParameter("email") ;
        String newPassword = request.getParameter("password") ;

        System.out.println("Mi è arrivato la pass: " + newPassword);

        //set te error string to show
        String errors = ""; ;
        boolean res  = false ;

        //call the checkPassword method
        try
        {
             res = service.checkCredentials(clientMail,newPassword) ;
        }
        catch(IllegalArgumentException exception)
        {
            errors  = errors + exception.getMessage();
        }


        System.out.println("Ecco gli errori: " + errors + "\narriva:" + res);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
