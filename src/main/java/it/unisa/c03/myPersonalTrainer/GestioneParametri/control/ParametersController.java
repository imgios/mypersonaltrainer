package it.unisa.c03.myPersonalTrainer.GestioneParametri.control;


import com.google.gson.Gson;
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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String weight = request.getParameter("weight");
        String mm = request.getParameter("leanMass");
        String mg = request.getParameter("fatMass");
        ParametersService service = new ParametersServiceImpl();
        String res = "";

        try {
            Parameters p = service.createParameters(weight, mm, mg);
            res = new Gson().toJson(1);
        } catch (IllegalArgumentException e) {
            res = new Gson().toJson(e.getMessage());
            response.getWriter().write(res);
            return;
        }
        response.getWriter().write(res);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
