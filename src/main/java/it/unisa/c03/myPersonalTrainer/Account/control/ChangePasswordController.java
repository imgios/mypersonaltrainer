package it.unisa.c03.myPersonalTrainer.Account.control;

import it.unisa.c03.myPersonalTrainer.Account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.Account.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePasswordController extends HttpServlet {

    AccountService service = new AccountServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //TO DO: Check if user is logged with session attribute.
        // if not, redirect on the homepage

        //Get parameters form the request form
        String clientMail = request.getParameter("email") ;
        String newPassword = request.getParameter("password") ;

        //set te error string to show
        String errors = ""; ;
        boolean checkResult  = false ;

        //call the service method to check the credentials
        try
        {
            checkResult = service.checkCredentials(clientMail,newPassword) ;
        }
        catch(IllegalArgumentException exception)
        {
            errors  = errors + exception.getMessage();
        }

        // if result is false, it means that there's an error to show
        if(!checkResult){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("AccountProfile.jsp");
        }
        // if result is true, it means that email and password have the right format.
        else if (checkResult == true){


            // check if mail exists in the db
            if(service.searchAccountByEmail(clientMail) == true)
            {
                /*TO DO
                 * if esiste
                 * se è uguale alla mail in session allora tutto ok, cambiaPassword
                 * altrimenti scrivi errore: questa non è la tua mail
                 * */
                service.changePassword(clientMail,newPassword);

                request.getSession().removeAttribute("errorToShow");
                request.getSession().setAttribute("successToShow", "Password modificata!");
                response.sendRedirect("AccountProfile.jsp");
            }
            // if mail doesn't exist in the DB
            else if(service.searchAccountByEmail(clientMail) == false){
                request.getSession().removeAttribute("successToShow");
                request.getSession().setAttribute("errorToShow", "Attenzione, questa non è la tua email!");
                response.sendRedirect("AccountProfile.jsp");
            }


        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
