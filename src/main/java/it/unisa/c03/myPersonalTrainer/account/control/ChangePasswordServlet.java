package it.unisa.c03.myPersonalTrainer.account.control;


import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {

    /**
     * @exclude
     * */
    private AccountDAO account = new AccountDAOImpl();
    /**
     * @exclude
     * */
    private AccountService service = new AccountServiceImpl(account);

    /**
     * This method checks the credentials arrived from the request,
     * and calls all the service methods which it needs to change
     * the password.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {


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
            response.sendRedirect("accountProfile.jsp");
        } else if (checkResult) {
            // if result is true, it means that email and password have
            // the right format.

            // Password encoding MD5
            String passwordToHash = newPassword;
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

            newPassword = generatedPassword;

            // check if mail exists in the db
            try {
                if (service.searchAccountByEmail(clientMail)) {

                    String emailInSession = (String)
                            request.getSession().getAttribute("clienteMail");
                    if (clientMail.equals(emailInSession)) {
                        //check if the mail entered is equals to
                        // the user's mail in session
                        service.changePassword(clientMail, newPassword);

                        request.getSession().removeAttribute("errorToShow");
                        request.getSession().setAttribute("successToShow",
                                "Password modificata!");
                        response.sendRedirect("accountProfile.jsp");
                    } else {
                        //if the email isnt the right one in session
                        request.getSession().removeAttribute("successToShow");
                        request.getSession().setAttribute("errorToShow",
                                "Attenzione, questa non è la tua email!");
                        response.sendRedirect("accountProfile.jsp");
                    }


                } else if (!(service.searchAccountByEmail(clientMail))) {
                    // if mail doesn't exist in the DB
                    request.getSession().removeAttribute("successToShow");
                    request.getSession().setAttribute("errorToShow",
                            "Attenzione, questa non è la tua email!");
                    response.sendRedirect("accountProfile.jsp");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

    }
    /**
     * This method simply recall doPost.
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

