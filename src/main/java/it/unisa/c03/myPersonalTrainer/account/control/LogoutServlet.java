package it.unisa.c03.myPersonalTrainer.account.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Logout.
 */

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); //Fetch session object
        if (session != null) { //If session is not null
            //removes all session attributes bound to the session
            session.invalidate();
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("Logout effettuato con successo");
        }
    }
}
