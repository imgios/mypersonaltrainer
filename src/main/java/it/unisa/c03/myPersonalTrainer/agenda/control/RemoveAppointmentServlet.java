package it.unisa.c03.myPersonalTrainer.agenda.control;

import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService;
import it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

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
    private static final int PORT = 465;
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String data = request.getParameter("dataapp");
        String time = request.getParameter("ora");
        String mail = request.getParameter("mail");
        Appointment appointment = new Appointment(data, time, mail);
        AgendaDAO dao = new AgendaDAOImpl();
        AgendaService service = new AgendaServiceImpl(dao);
        response.setContentType("application/json");
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(
                    "mypt.gps.is@gmail.com", "mypt2021"));
            email.setSSLOnConnect(true);
            email.setFrom("mypt.gps.is@gmail.com");
            email.setSubject("Appuntamento Cancellato");
            email.setMsg("L'appuntamento del giorno " + data + " alle ore "
                    + time + " e' stato cancellato!");
            email.addTo(mail);
            email.addTo("mypt.gps.is@gmail.com");
            email.send();

            response.getWriter().write(new Gson().toJson(service.
                    removeAppointment(appointment)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }
}
