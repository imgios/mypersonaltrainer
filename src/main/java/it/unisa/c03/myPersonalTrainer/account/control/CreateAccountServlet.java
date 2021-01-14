package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl;
import it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionService;
import it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionServiceImpl;
import org.apache.commons.mail.EmailException;

/**
 * servlet for creating account.
 */
@WebServlet(name = "Registrazione", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {

    /**
     * method do post.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role = 0;

        // Password encoding MD5
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff)
                        + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Account utente = new Account(name, surname, phone,
                email, generatedPassword, role);

        AccountDAO accountDao = new AccountDAOImpl();
        AccountService accountService = new AccountServiceImpl(accountDao);

        /*
          controlcredential check if the email is just register.
        */
        boolean controlcredential = false;

        /*
         control check if the information follow the exp.
         */
        boolean control = false;

       /* need to print the message of error
            into the page */
        String errors = "";

        try {
            controlcredential = accountService
                    .checkCredentials(email, password);
            if (controlcredential) {
                //System.out.println("credenziali OK");
                control = accountService.registerAccount(utente);

                // subscription registration
                SubscriptionDAO subDao = new SubscriptionDAOImpl();
                SubscriptionService subService =
                        new SubscriptionServiceImpl(subDao);
                subService.createSubscription(utente.getEmail());

                //Notifica di creazione
                accountService.sendEmail(email, password);

            }
        } catch (IllegalArgumentException | ExecutionException | InterruptedException | EmailException exception) {
            errors = errors + exception.getMessage();
        }

        if (controlcredential && control) {
            request.getSession().removeAttribute("errorMessage");
            request.getSession().setAttribute("successMessage",
                    "Inserimento riuscito");
            response.sendRedirect("adminDashboard.jsp");
        } else if (controlcredential && !control) {
            request.getSession().removeAttribute("successMessage");
            request.getSession().setAttribute("errorMessage", errors);
            response.sendRedirect("createAccount.jsp");
        } else if (!controlcredential && !control) {
            request.getSession().removeAttribute("successMessage");
            request.getSession().setAttribute("errorMessage", errors);
            response.sendRedirect("createAccount.jsp");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
