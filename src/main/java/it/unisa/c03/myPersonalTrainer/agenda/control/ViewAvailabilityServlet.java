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
import java.util.concurrent.ExecutionException;

/**
 * this class controls the interaction between personal trainer and system.
 * return 1 if the insertion is done, 0 not done, 2 if the availability already exists
 */
@WebServlet(name = "ViewAvailabilityServlet",
        value = "/view-availability")
public class ViewAvailabilityServlet extends HttpServlet {
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


       /*response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");*/

        String data = request.getParameter("dataSelected");
        System.out.println(data);

        String res = "";


        agendaService.checkDate(data);

        try {
            ArrayList<Availability> list = (ArrayList<Availability>) agendaService.getAvailabilityByDate(data);

            if (list.size() == 0) {

                res = new Gson().toJson(0);
            } else {
                res = new Gson().toJson(list);
                System.out.println(list.size());
                System.out.println(list);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
