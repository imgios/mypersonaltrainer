package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "RemoveAppointmentServlet",
        value = "/RemoveAppointmentServlet")
public class RemoveAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        System.out.println("ci entro");
        String data = request.getParameter("dataapp");
        String time = request.getParameter("ora");
        String mail = request.getParameter("mail");
        Appointment appointment = new Appointment(data, time, mail);
        AgendaDAO dao = new AgendaDAOImpl();
        AgendaService service = new AgendaServiceImpl(dao);
        response.setContentType("application/json");
        try {
            response.getWriter().write(new Gson().toJson(service.
                    removeAppointment(appointment)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
