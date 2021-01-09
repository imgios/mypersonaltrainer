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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

/**
 * this class controls the interaction between personal
 * trainer and system to view personal trainer availability.
 * return 1+"Message" if there is an error in the date input ,
 * 0 if there aren't availability, else return the
 * availability list for this date.
 */
@WebServlet(name = "ViewAvailabilityServlet",
        value = "/view-availability")
public class ViewAvailabilityServlet
        extends HttpServlet {
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

    /**
     *parametro fittizio per effettuare solo il check sulla data.
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {


        final String TIME_PASS = "15";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        /**
         * data presa dalla jsp
         */
        String data =
                request.getParameter("dataSelected");
        /**
         * stringa che verra' data in output in formato json
         */
        String res = "";


        try {
            agendaService.checkAvailability(
                    data, TIME_PASS);
            ArrayList<Availability> list =
                    (ArrayList<Availability>)
                            agendaService.getAvailabilityByDate(data);
            if (list.size() == 0) {

                res = new Gson().toJson(0);
                //non ci sono disponibilita per quella data.
            } else { //ritorna le disponibilita' per la data scelta
                list.sort(Comparator.comparing(Availability::getTime));
                res = new Gson().toJson(list);
            }
        } catch (IllegalArgumentException
            | InterruptedException | ExecutionException e) { //ritorna il valore 1+il messaggio del relativo errore. 1 e' un valore sentinella.
            res = new Gson().toJson(
                    "1" + e.getMessage());
            //formato non valido o data precedente
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
