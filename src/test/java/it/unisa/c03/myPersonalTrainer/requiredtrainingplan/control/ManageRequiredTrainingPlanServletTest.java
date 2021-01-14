package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ManageRequiredTrainingPlanServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequiredTrainingPlanServiceImpl requiredService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);

    RequiredTrainingPlanDAO requiredTrainingDAO= Mockito.mock(RequiredTrainingPlanDAOImpl.class);
    RequiredTrainingPlan requireB = Mockito.mock(RequiredTrainingPlan.class);
    RequiredTrainingPlanServiceImpl requiredTrainingPlanService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);

    String email = "marcorossi@gmail.com";
    int required = 1;

    @Test
    void doPostCatchIAE() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);

        assertTrue(requiredService.registerRequest(requiredPlan));
        new ManageRequiredTrainingPlanServlet().doPost(request,response);
    }

    @Test
    void doPostCredTrue() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);

        assertTrue(requiredService.registerRequest(requiredPlan));
        new ManageRequiredTrainingPlanServlet().doPost(request,response);

    }

    @AfterAll
    static void afterinsertdelete() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("RequiredTrainingPlan").whereEqualTo("email","marcorossi@gmail.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @Test
    void doPostCredTrueandControl() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),anyString());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);
        assertTrue(requiredService.registerRequest(requiredPlan));
        new ManageRequiredTrainingPlanServlet().doPost(request,response);

    }

    @Test
    void doPostCredFalse() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);

        assertTrue(requiredService.registerRequest(requiredPlan));
        new ManageRequiredTrainingPlanServlet().doPost(request,response);

    }

    @Test
    void doPostPass() throws IOException, ServletException, ExecutionException, InterruptedException {
        Mockito.when(request.getParameter("email")).thenReturn(email);
        assertEquals(email, request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn(String.valueOf(required));
        assertEquals( "1", request.getParameter("required"));

        requireB= new RequiredTrainingPlan(request.getParameter("email"), request.getIntHeader("required"));
        requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingDAO);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(),any());
        doNothing().when(response).sendRedirect(anyString());

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);

        assertTrue(requiredService.registerRequest(requireB));

        new ManageRequiredTrainingPlanServlet().doPost(request,response);
    }

    @Test
    void doPostFalse() throws IOException, ServletException, ExecutionException, InterruptedException {

        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));

        RequiredTrainingPlan user = new RequiredTrainingPlan(request.getParameter("email"), request.getIntHeader("required"));

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(false);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(), any());
        doNothing().when(response).sendRedirect(anyString());

        new ManageRequiredTrainingPlanServlet().doPost(request,response);
    }

    @Test
    void doGet() throws IOException, ServletException, ExecutionException, InterruptedException {

        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(), any());
        doNothing().when(response).sendRedirect(anyString());

        new ManageRequiredTrainingPlanServlet().doGet(request,response);
    }
    
}
