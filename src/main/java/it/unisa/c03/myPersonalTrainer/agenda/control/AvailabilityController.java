package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AvailabilityController",
        value = "/availability-controller")
public class AvailabilityController extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String data = request.getParameter("dataSelected");
        System.out.println("ho ricevuto " + data);

        AgendaService agendaService = new AgendaServiceImpl();

        String x = request.getParameter("timeSelected");

        String res = "";
        try {
            System.out.println(agendaService.checkAvailability(data, x));
            Availability avaiability =
                    new Availability(data, Integer.parseInt(x));
            agendaService.createAvailability(avaiability);
            res = new Gson().toJson(1);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            res = new Gson().toJson(e.getMessage());
            response.getWriter().write(res);
            return;
        }
        response.getWriter().write(res);
        return;
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
