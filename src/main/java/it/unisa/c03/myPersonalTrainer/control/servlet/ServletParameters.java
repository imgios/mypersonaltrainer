package it.unisa.c03.myPersonalTrainer.control.servlet;


import it.unisa.c03.myPersonalTrainer.model.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.control.serviceImpl.ParametersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletParameters", value = "/ServletParameters")
public class ServletParameters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParametersServiceImpl service = new ParametersServiceImpl();
        String weight = request.getParameter("weight");
        String mm = request.getParameter("leanMass");
        String mg = request.getParameter("fatMass");
        System.out.println("ho ricevuto "+weight+mg+mm);

        try {
            /*service.controllafatMass(mg);
            service.controllaleanMass(mm);
            service.controllaweight(weight);*/
          Parameters p = service.acceptParameters(weight,mm,mg);

           System.out.println(p.toString());

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("ahahahhha");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
