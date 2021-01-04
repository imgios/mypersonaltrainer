package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        boolean verifiedCredential;

        try {
            verifiedCredential = accountService.
                    checkCredentials(email, password);
            if (verifiedCredential) {
                if (accountService.loginAccount(email, password)) {
                    utente = accountDao.
                            findAccountByEmail(email);
                    testUtente = new Account(utente.getName(),
                            utente.getSurname(), utente.getPhone(),
                            utente.getEmail(), utente.getPassword(),
                            utente.getRole());
                    if (email.equals(testUtente.getEmail())
                            && password.equals(testUtente.getPassword())) {
                        if (accountService.verifyIsAdmin(utente)) {
                            request.getSession().setAttribute("ptMail",email);
                            response.sendRedirect("adminDashboard.jsp");
                        } else {
                            request.getSession().setAttribute("clienteMail",email);
                            response.sendRedirect("clienteDashboard.jsp");
                        }
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
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
