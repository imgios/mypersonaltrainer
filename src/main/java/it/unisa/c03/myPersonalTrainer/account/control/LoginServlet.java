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

/**
 * Servlet that allow the Account's login.
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Method doPost.
     * @param request The request include the content
     * of Login form at the moment of Account's authentication.
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountDAO accountDao = new AccountDAOImpl();
        AccountService accountService = new AccountServiceImpl(accountDao);

        Account utente;
        Account testUtente;
        boolean verifiedCredential = false;
        boolean control = false;
        String errors = ""; // Stringa per catturare l'errore.

        /*
         * using checkCredentials method, we'll verify if
         *  credentials follows
         * regular expression:
         * return true if correct
         * the boolean will stored in verifiedCredential.
         */
        try {
            verifiedCredential = accountService.
                checkCredentials(email, password);
            /*
             * Now we'll analize the returned value, stored in
             * verifiedCredentials
             * If true we'll continue to next if
             * instead we return customer to Login page(with an alert).
             */
            if (verifiedCredential) {

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

                password = generatedPassword;

                control = accountService.loginAccount(email, password);
                /*
                 * Now we'll analize that loginAccount method after
                 * the research of account
                 * given login's credentials.
                 * the boolean will stored in control.
                 * if true: we'll continue getting all fields
                 * of relative account;
                 * so, we'll create a testing Account to
                 * continue testing.
                 */
                if (control) {
                    utente = accountDao.findAccountByEmail(email);
                    testUtente = new Account(
                        utente.getName(),
                        utente.getSurname(),
                        utente.getPhone(),
                        utente.getEmail(),
                        utente.getPassword(),
                        utente.getRole());
                    /*
                     * Now, continuing with test, we'll verify, if email
                     * and password of
                     * the login form are the same of Account obtained
                     * from the previous check.
                     * The user has correctly credential, and we'll
                     * verify it's role.
                     * Instead, the user will redirect to login
                     * form(with an error alert).
                     */
                    if (email.equals(testUtente.getEmail())
                        && password.equals(testUtente.getPassword())) {
                        /*
                         * This last check is used to identify the
                         * role of logged user
                         * thanks to verifyIsAdmin method:
                         * true if his role is 1 and so "Admin" or
                         * "personal Trainer"
                         * false if his role is 0 and so "Customer".
                         * after that the user will be redirect to
                         * his dedicated Dashboard.
                         */
                        if (accountService.verifyIsAdmin(utente)) {
                            request.getSession().setAttribute("ptMail", email);
                            response.sendRedirect("adminDashboard.jsp");
                        } else {
                            request.getSession().
                                    setAttribute("clienteMail", email);
                            response.sendRedirect("customerDashboard.jsp");
                        }
                    } else if (!(email.equals(testUtente.getEmail()))) {
                        request.getSession().
                                removeAttribute("successInsertLogin");
                        request.getSession().
                                setAttribute("errorInsertLogin",
                                        "Email errata!");
                        response.sendRedirect("login.jsp");
                    } else if (!(password.equals(testUtente.getPassword()))) {
                        request.getSession().
                                removeAttribute("successInsertLogin");
                        request.getSession().
                                setAttribute("errorInsertLogin",
                                        "Password errata!");
                        response.sendRedirect("login.jsp");
                    }
                }
            }
        } catch (IllegalArgumentException | ExecutionException
            | InterruptedException exception) {
            errors = errors + exception.getMessage();
        }

        if (verifiedCredential && !control) {
            request.getSession().removeAttribute("successInsertLogin");
            request.getSession().setAttribute("errorInsertLogin", errors);
            response.sendRedirect("login.jsp");
        } else if (!verifiedCredential && !control) {
            request.getSession().removeAttribute("successInsertLogin");
            request.getSession().setAttribute("errorInsertLogin", errors);
            response.sendRedirect("login.jsp");
        }
    }
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }
}
