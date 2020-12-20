package it.unisa.c03.myPersonalTrainer.control.servlet;

import it.unisa.c03.myPersonalTrainer.model.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profiloUtenteServlet", value = "/profiloutente-servlet")
public class ProfiloUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer mic = new Customer("Michele" , "ian", "email", "pass", 1);

        String nuovaPassword = request.getParameter("passwordR") ;

        System.out.println("Mi è arrivato pass: " + nuovaPassword);

        System.out.println("Vecchia pass: " + mic.getPassword());

        mic.setPassword(nuovaPassword);

        System.out.println("Nuova pass: " + mic.getPassword());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
