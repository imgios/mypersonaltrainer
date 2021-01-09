package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl;
import it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionService;
import it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionServiceImpl;

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

        //valori passati dalla form jsp
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role = 0;

        //creazione nuovo oggetto

        Account utente = new Account(name, surname, phone,
                email, password, role);

        //verifica informazioni acquisite dalla servlet
        //System.out.println("---------------------------");
        //System.out.println(utente);

        //richiamo della funzione per registrare un nuovo account
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

        /*permette di stampare l'errore a
        video una volta catturato dall'eccezione */
        String errors = "";

        try {
            controlcredential = accountService
                    .checkCredentials(email, password);
            if (controlcredential) {
                System.out.println("credenziali OK");

                control = accountService.registerAccount(utente);

                // subscription registration
                SubscriptionDAO subDao = new SubscriptionDAOImpl();
                SubscriptionService subService = new SubscriptionServiceImpl(subDao);
                subService.createSubscription(utente.getEmail());

                if (control) {
                    System.out.println("boolean true inserito");
                    System.out.println("valore inserito inserito");
                    System.out.println("Salvataggio nuovo utente");
                } else {
                    System.out.println("boolean false non inserimento");
                }
            } else {
                System.out.println("le credenziali non sono valide.");
            }
        } catch (IllegalArgumentException
                | ExecutionException | InterruptedException exception) {
            errors = errors + exception.getMessage();
        }

        /*
        l'errore catturato tramite un avviso e il send redirect può
        essere visualizzato a video
        ho individuato tre casi di errore per visualizzare a schermo
        gestione errore
         if result is false, it means that there's an error to show
        memorizzazzione okay e informazioni non presenti
         */

        if (controlcredential && control) {
            request.getSession().removeAttribute("errorToShow");
            request.getSession().setAttribute("successToShow",
                    "Inserimento riuscito");
            response.sendRedirect("createAccount.jsp");
        } else if (controlcredential && !control) {
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("createAccount.jsp");
        } else if (!controlcredential && !control) {
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("createAccount.jsp");
        } else if (!controlcredential && control) {
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("createAccount.jsp");
        }

        //
        //email o credenziali non valide
      /*
        else if (control == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("createAccount.jsp");
        }
        else if (control == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("createAccount.jsp");
        }
       */
        /*
        control = accountService.RegisterAccount(utente);

        if(control == true){
            System.out.println("boolean true inserito");
            System.out.println("valore inserito inserito");

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>hai salvato il nuovo utente, grande</h1>");
            out.println("</body></html>");
        }else{
            System.out.println("boolean false inserito");
            System.out.println("t'attacchi belllo");

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>non c'è niente di bello da vedere</h1>");
            out.println("</body></html>");
        }
        */

        /*
        AccountDAO accountDAO = new AccountDAOImpl();
        accountDAO.saveAccount(utente);
        Hello----stampa messaggio di funzionamento
         PrintWriter out = response.getWriter();
         out.println("<html><body>");
         out.println("<h1>ciao, guarda il log</h1>");
         out.println("</body></html>");
        */
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
