package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.control;
/*
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.c03.myPersonalTrainer.account.control.ChangePasswordServlet;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.control.ManageRequiredTrainingPlanServlet;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

    class ManageRequiredTrainingPlanServletTest {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequiredTrainingPlanServiceImpl requireService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);
        RequiredTrainingPlanDAO requiredTrainingDAO= Mockito.mock(RequiredTrainingPlanDAOImpl.class);
        RequiredTrainingPlan requireB = Mockito.mock(RequiredTrainingPlan.class);
        RequiredTrainingPlanServiceImpl requiredTrainingPlanService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);

        boolean checked = false;

        String email = "marcorossi@gmail.com";
        int required = 1;
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

            Mockito.when(requireService.registerRequest(Mockito.any())).thenReturn(true);

            assertTrue(requireService.registerRequest(requireB));

            new ManageRequiredTrainingPlanServlet().doPost(request,response);
        }

        @Test
        void doPostFalse() throws IOException, ServletException, ExecutionException, InterruptedException {

            Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
            assertEquals("test@test.pt", request.getParameter("email"));
            Mockito.when(request.getParameter("required")).thenReturn("0");
            assertEquals("0", request.getParameter("required"));

            RequiredTrainingPlan user = new RequiredTrainingPlan(request.getParameter("email"), request.getIntHeader("required"));

            Mockito.when(requireService.registerRequest(Mockito.any())).thenReturn(false);

            HttpSession session = Mockito.mock(HttpSession.class);
            Mockito.when(request.getSession()).thenReturn(session);

            doNothing().when(session).removeAttribute(anyString());
            doNothing().when(session).setAttribute(anyString(), any());
            doNothing().when(response).sendRedirect(anyString());

            new ManageRequiredTrainingPlanServlet().doPost(request,response);
        }

        @Test
        void doGet() throws IOException, ServletException, ExecutionException, InterruptedException {

            Mockito.when(request.getParameter("username")).thenReturn("TestUsername");
            //assertEquals("TestUsername", request.getParameter("username"));
            Mockito.when(request.getParameter("surname")).thenReturn("TestSurname");
            Mockito.when(request.getParameter("phone")).thenReturn("0000000000");
            Mockito.when(request.getParameter("email")).thenReturn("test@test.it");
            Mockito.when(request.getParameter("password")).thenReturn("Test001");
            Mockito.when(request.getParameter("role")).thenReturn("0");

            Account user = new Account (request.getParameter("username"),
                    request.getParameter("surname"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("passowrd"), request.getIntHeader("role"));

            Mockito.when(requireService.registerRequest(any())).thenReturn(true);

            HttpSession session = Mockito.mock(HttpSession.class);
            Mockito.when(request.getSession()).thenReturn(session);

            doNothing().when(session).removeAttribute(anyString());
            doNothing().when(session).setAttribute(anyString(), any());
            doNothing().when(response).sendRedirect(anyString());


            //new CreateAccountServlet().doPost(request,response);
            new ManageRequiredTrainingPlanServlet().doGet(request,response);
        }
    }
*/



   // package it.unisa.c03.myPersonalTrainer.account.control;

            import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.anyString;
        import static org.mockito.Mockito.doNothing;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.when;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.ArgumentMatchers.anyString;


        import com.google.cloud.firestore.QueryDocumentSnapshot;
        import it.unisa.c03.myPersonalTrainer.account.bean.Account;
        import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
        import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
        import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
        import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
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




class CreateAccountServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequiredTrainingPlanServiceImpl requiredService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);

   // RequiredTrainingPlanServiceImpl requireService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);
    RequiredTrainingPlanDAO requiredTrainingDAO= Mockito.mock(RequiredTrainingPlanDAOImpl.class);
    RequiredTrainingPlan requireB = Mockito.mock(RequiredTrainingPlan.class);
    RequiredTrainingPlanServiceImpl requiredTrainingPlanService = Mockito.mock(RequiredTrainingPlanServiceImpl.class);

    boolean checked = false;

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

/*NEW*/
  @AfterAll
  static void afterinsertdelete() throws IOException, ExecutionException, InterruptedException {
    List<QueryDocumentSnapshot> lqds = DBConnection
        .getConnection().collection("RequiredTrainingPlan").whereEqualTo("email","marcorossi@gmail.com").get().get().getDocuments();
    for(QueryDocumentSnapshot document : lqds)
    {
      document.getReference().delete();
    }
  }
/*END NEW*/



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

    @AfterAll
    static void afterinseraccounttest() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("Account").whereEqualTo("email","marcorossi@gmail.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
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

/*NEW*/

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

    /*END NEW*/
    @Test
    void doGet() throws IOException, ServletException, ExecutionException, InterruptedException {

        Mockito.when(request.getParameter("email")).thenReturn("test@test.pt");
        assertEquals("test@test.pt", request.getParameter("email"));
        Mockito.when(request.getParameter("required")).thenReturn("0");
        assertEquals("0", request.getParameter("required"));
        RequiredTrainingPlan requiredPlan = new RequiredTrainingPlan (request.getParameter("email"), request.getIntHeader("required"));

        Mockito.when(requiredService.registerRequest(Mockito.any())).thenReturn(true);
        //Mockito.when(requiredService.registerRequest()any())).thenReturn(true);

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        doNothing().when(session).removeAttribute(anyString());
        doNothing().when(session).setAttribute(anyString(), any());
        doNothing().when(response).sendRedirect(anyString());

        new ManageRequiredTrainingPlanServlet().doGet(request,response);
    }


}