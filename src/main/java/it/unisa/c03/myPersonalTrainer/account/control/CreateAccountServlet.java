package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;


@WebServlet(name = "Registrazione", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //valori passati dalla form jsp
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role = 0;

        //creazione nuovo oggetto

        Account utente = new Account(name, surname, phone, email, password, role);

        //verifica informazioni acquisite dalla servlet
        //System.out.println("---------------------------");
        //System.out.println(utente);


        //richiamo della funzione per registrare un nuovo account
        AccountService accountService = new AccountServiceImpl();

        boolean controllo_credenziali = false;
        boolean controllo = false;

        //permette di stampare l'errore a video una volta catturato dall'eccezione
        String errors="";

        try {
            controllo_credenziali = accountService.checkCredentials(email, password);
            if (controllo_credenziali == true) {
                System.out.println("credenziali OK");

                controllo = accountService.RegisterAccount(utente);

                if (controllo == true) {
                    System.out.println("boolean true inserito");
                    System.out.println("valore inserito inserito");

                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h1>hai salvato il nuovo utente, grande</h1>");
                    out.println("</body></html>");
                } else {
                    System.out.println("boolean false inserito");
                    System.out.println("t'attacchi belllo");

                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h1>non c'è niente di bello da vedere</h1>");
                    out.println("</body></html>");
                }

            } else {
                System.out.println("le credenziali non sono valide.");
            }
        }catch(IllegalArgumentException exception)
            {
                errors  = errors + exception.getMessage();
            }
    //l'errore catturato tramite un avviso e il send redirect può essere visualizzato a video
//ho individuato tre casi di errore per visualizzare a schermo
    //gestione errore
        // if result is false, it means that there's an error to show
        //memorizzazzione okay e informazioni non presenti
        if(controllo_credenziali == true && controllo == true){
            request.getSession().removeAttribute("errorToShow");
            request.getSession().setAttribute("successToShow", "Inserimento riuscito");
            response.sendRedirect("CreateAccount.jsp");
        }

        // controllo credenziali okay ma email già presente nel db

        else if (controllo_credenziali == true && controllo == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("CreateAccount.jsp");
        }
        else if (controllo_credenziali == false && controllo == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("CreateAccount.jsp");
        }
        else if (controllo_credenziali == false && controllo == true){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("CreateAccount.jsp");
        }

        //
        //email o credenziali non valide
      /*
        else if (controllo == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("CreateAccount.jsp");
        }
        else if (controllo == false){
            request.getSession().removeAttribute("successToShow");
            request.getSession().setAttribute("errorToShow", errors);
            response.sendRedirect("CreateAccount.jsp");
        }
       */
        /*
        controllo = accountService.RegisterAccount(utente);

        if(controllo == true){
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

        //AccountDAO accountDAO = new AccountDAOImpl();
        //accountDAO.saveAccount(utente);


        //Hello----stampa messaggio di funzionamento
       // PrintWriter out = response.getWriter();
       // out.println("<html><body>");
       // out.println("<h1>ciao, guarda il log</h1>");
       // out.println("</body></html>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
