package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * this class controls the interaction between personal trainer and system.
 * return 1 if the insertion is done, 0 not done, 2 if the availability already exists
 */
@WebServlet(name = "AvailabilityController",
        value = "/availability-controller")
public class AvailabilityController extends HttpServlet {
    /**
     * AgendaDAO
     */
    private AgendaDAO agendaDAO =
            new AgendaDAOImpl();
    /**
     * AgendaService to use the service methods
     */
    private AgendaService agendaService =
            new AgendaServiceImpl(agendaDAO);

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String data = request.getParameter("dataSelected");
        System.out.println("ho ricevuto " + data);


        String x = request.getParameter("timeSelected");

        String res = "";
        try {
            System.out.println(
                    agendaService.checkAvailability(data, x));

            Availability prova = agendaService.getAvailabilityByDateAndTime(data, Integer.parseInt(x));
            System.out.println(prova);
            if (prova == null) {
                Availability avaiability =
                        new Availability(data, Integer.parseInt(x));
                agendaService.createAvailability(avaiability);
                res = new Gson().toJson(1);
            } else {
                res = new Gson().toJson(2);
            }
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
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
