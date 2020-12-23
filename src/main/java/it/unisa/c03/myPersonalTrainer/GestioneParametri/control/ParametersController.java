package it.unisa.c03.myPersonalTrainer.GestioneParametri.control;


import it.unisa.c03.myPersonalTrainer.GestioneParametri.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.service.ParametersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
//to do -> prendere la mail del cliente dalla sessione, dopo che il login è stato fatto. cambiare il costruttore

@WebServlet(name = "ParametersController", value = "/parameters-controller")
public class ParametersController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParametersService service = new ParametersServiceImpl();
        String weight = request.getParameter("weight");
        String mm = request.getParameter("leanMass");
        String mg = request.getParameter("fatMass");

        try {
           /* Parameters p = service.createParameters(weight, mm, mg);
            System.out.println(p.toString());
            request.getSession().setAttribute("done", "Inserimento parametri completato con successo");
            //resp.sjon tt ok

            */

            ArrayList<Parameters> p = service.getByMail("prova@io.it");
            if (p == null || p.size() < 1) {
                System.out.println("non ce");
            } else {
                for (Parameters parameters : p) {
                    System.out.println(parameters.toString());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("ahahahhha");
            request.getSession().setAttribute("error", e.getMessage());


            //add resp.json
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
