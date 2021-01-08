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
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

@WebServlet(name = "AllAppointmentsServlet", value = "/AllAppointmentsServlet")
public class AllAppointmentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        AgendaDAO dao = new AgendaDAOImpl();
        AgendaService service = new AgendaServiceImpl(dao);
        List<Appointment> lista = null;
        String data = request.getParameter("data");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            lista = service.findAppointmentByDate(data);
            if (lista.size()==0) {
                out.print(new Gson().toJson(0));
            }
            out.print(new Gson().toJson(lista));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
