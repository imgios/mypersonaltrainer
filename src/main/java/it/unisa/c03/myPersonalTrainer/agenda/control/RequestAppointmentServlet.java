package it.unisa.c03.myPersonalTrainer.agenda.control;

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

@WebServlet(name = "RequestAppointmentServlet")
public class RequestAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws
            ServletException, IOException {
        String data = request.getParameter("data");
        String mail = request.getParameter("mailutente");
        String time = request.getParameter("time");
        AgendaDAO dao = new AgendaDAOImpl();
        AgendaService service = new AgendaServiceImpl(dao);

        if (service.checkDate(data)) {
            try {
                service.createAppointment(data, time, mail);
                request.getSession().setAttribute("successToShow",
                        "Appuntamento programmato");
                response.sendRedirect("RequestAppointment.jsp");
            } catch (ExecutionException e) {
                request.getSession().setAttribute("errorToShow",
                        "errore nel programmare l'appuntamento");
                response.sendRedirect("RequestAppointment.jsp");
            } catch (InterruptedException e) {
                request.getSession().setAttribute("errorToShow",
                        "errore nel programmare l'appuntamento");
                response.sendRedirect("RequestAppointment.jsp");
            }

        } else {
            request.getSession().setAttribute("errorToShow",
                    "errore nel programmare l'appuntamento");
            response.sendRedirect("RequestAppointment.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doPost(request, response);

    }
}
