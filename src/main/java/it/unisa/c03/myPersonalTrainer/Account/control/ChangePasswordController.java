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


        //TO DO: check if email exists in db
        // with find by email

        //set te error string to show
        String errors = ""; ;
        boolean result  = false ;

        //call the service method
        try
        {
            result = service.checkCredentials(clientMail,newPassword) ;
        }
        catch(IllegalArgumentException exception)
        {
            errors  = errors + exception.getMessage();
        }

        // if result is false, it means that there's an error to show
        if(!result){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("AccountProfile.jsp");
        }
        // if result is true, show the success to the user
        else if (result == true){
            request.getSession().removeAttribute("errorToShow");
            request.getSession().setAttribute("successToShow", "Password modificata!");
            response.sendRedirect("AccountProfile.jsp");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
