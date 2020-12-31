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
 * servlet for Login account.
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

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
                utente = accountDao.
                        findAccountByEmail(email);
                testUtente = new Account(utente.getName(),
                        utente.getSurname(), utente.getPhone(),
                        utente.getEmail(), utente.getPassword(),
                        utente.getRole());
                if (utente.getEmail() != null) {
                    if (email.equals(testUtente.getEmail())
                            && password.equals(testUtente.getPassword())) {
                        if (accountService.verifyIsAdmin(utente)) {
                            System.out.println("Bentornato Personal Trainer "
                                    + testUtente.getName() + " !");
                            System.out.println(testUtente.toString());//TEST
                            response.sendRedirect("adminDashboard.jsp");
                        } else {
                            System.out.println("Bentornato Utente "
                                    + testUtente.getName() + " !");
                            response.sendRedirect("clienteDashboard.jsp");
                        }
                    } else {
                        System.out.println("Credenziali errate");
                        response.sendRedirect("login.jsp");
                    }
                } else {
                    System.out.println("Credenziali errate");
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    final protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
