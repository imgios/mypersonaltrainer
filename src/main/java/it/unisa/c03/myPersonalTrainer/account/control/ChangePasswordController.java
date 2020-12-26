package it.unisa.c03.myPersonalTrainer.account.control;


import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePasswordController extends HttpServlet {

    private AccountService service = new AccountServiceImpl();

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServletException, IOException {

        //TO DO: Check if user is logged with session attribute.
        // if not, redirect on the homepage

        //Get parameters form the request form
        String clientMail = request.getParameter("email");
        String newPassword = request.getParameter("password");

        //set te error string to show
        String errors = "";
        boolean checkResult  = false;

        //call the service method to check the credentials
        try {
            checkResult = service.checkCredentials(clientMail, newPassword);
        } catch (IllegalArgumentException exception) {
            errors  = errors + exception.getMessage();
        }

        // if result is false, it means that there's an error to show
        if (!checkResult) {
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("AccountProfile.jsp");
        } else if (checkResult) {
            // if result is true, it means that email and password have
            // the right format.


            // check if mail exists in the db
            if (service.searchAccountByEmail(clientMail)) {
                /*TO DO
                 * if esiste
                 * se è uguale alla mail in session allora
                 * tutto ok, cambiaPassword
                 * altrimenti scrivi errore: questa non è la tua mail
                 * */
                service.changePassword(clientMail, newPassword);

                request.getSession().removeAttribute("errorToShow");
                request.getSession().setAttribute("successToShow",
                        "Password modificata!");
                response.sendRedirect("AccountProfile.jsp");
            } else if (!(service.searchAccountByEmail(clientMail))) {
                // if mail doesn't exist in the DB
                request.getSession().removeAttribute("successToShow");
                request.getSession().setAttribute("errorToShow",
                        "Attenzione, questa non è la tua email!");
                response.sendRedirect("AccountProfile.jsp");
            }


        }

    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

