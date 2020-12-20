package it.unisa.c03.myPersonalTrainer;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletParameters", value = "/ServletParameters")
public class ServletParameters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParametersService service = new ParametersService();
        String peso = request.getParameter("peso");
        String mm = request.getParameter("massaMagra");
        String mg = request.getParameter("massaGrassa");
        System.out.println("ho ricevuto "+peso+mg+mm);

        try {
            /*service.controllaPeso(peso);
          service.controllaMM(mm);
            service.controllaMassagGrassa(mg);*/
          Parameters p =  service.acceptParams(peso,mm,mg);

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
