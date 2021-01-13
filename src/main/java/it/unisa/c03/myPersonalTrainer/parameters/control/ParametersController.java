package it.unisa.c03.myPersonalTrainer.parameters.control;


import com.google.gson.Gson;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this class manage the insert parameters.
 * if return 1 the insert passed, else return the oracle message.
 */
@WebServlet(name = "ParametersController",
        value = "/parameters-controller")
public class ParametersController
        extends HttpServlet {

    /**
     * private ParametersDAO parametersDAO =
     * new ParametersDAOImpl();
     */
    private ParametersDAO parametersDAO =
            new ParametersDAOImpl();
    /**
     * ParametersService to use the service methods
     */
    private ParametersService service =
            new ParametersServiceImpl(parametersDAO);


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String weight = request.getParameter("weight");
        String mm = request.getParameter("leanMass");
        String mg = request.getParameter("fatMass");
        String res = "";
        String mail = (String) request.getSession().getAttribute("clienteMail");

        try {
            Parameters p =
                    service.createParameters(weight, mm, mg, mail);

            if (p != null) {
                if (service.saveParameters(p)) {
                    res = new Gson().toJson(1);
                }
            }
        } catch (IllegalArgumentException e) {
            res = new Gson().toJson(e.getMessage());
            response.getWriter().write(res);
            return;
        }
        response.getWriter().write(res);
        return;
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
