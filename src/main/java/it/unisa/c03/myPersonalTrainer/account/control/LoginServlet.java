package it.unisa.c03.myPersonalTrainer.account.control;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  Servlet used to allow the login of an User.
 */
@WebServlet (name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * Create an AccountService Object.
     */
    private AccountService service = new AccountServiceImpl();
    private static final long serialVersionUID = 1L;
    //Protected generate a CheckStyle issue.
    final protected void doPost(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccountServiceImpl accountTry;
        accountTry = new AccountServiceImpl();
        AccountDAOImpl currentAccount = new AccountDAOImpl();
        Account analizeAccount;
        analizeAccount = currentAccount.login(email, password);
        if (accountTry.loginAccount(email, password)) {
            if (accountTry.verifyIsAdmin(analizeAccount)) {
                System.out.println("Bentornato Admin "
                        + analizeAccount.getName() + " !");
                response.sendRedirect("adminDashboard.jsp");
            } else {
                System.out.println("Bentornato Cliente "
                        + analizeAccount.getName() + " !");
                response.sendRedirect("clienteDashboard.jsp");
                 }
        } else {
            System.out.println("Credenziali inesistenti!");
            response.sendRedirect("login.jsp");
             }
    }
    /**
     *
     * @param request request get by the Login Form.
     * @param response response reserved to relative JSP.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
