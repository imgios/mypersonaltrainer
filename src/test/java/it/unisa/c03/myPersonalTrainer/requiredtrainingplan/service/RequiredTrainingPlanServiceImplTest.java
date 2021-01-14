package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class RequiredTrainingPlanServiceImplTest {

    @Test
    public void searchAccountByEmailFalse() throws InterruptedException, ExecutionException, IOException {
        RequiredTrainingPlanDAO requiredTrainingPlanDAO = Mockito.mock(RequiredTrainingPlanDAO.class) ;
        RequiredTrainingPlan a = new RequiredTrainingPlan();
        a.setEmail(null);
        Mockito.when(requiredTrainingPlanDAO.findAccountByEmail(anyString())).thenReturn(a);

        RequiredTrainingPlanService service  = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDAO);
        assertEquals(false, service.searchAccountByEmail("mailnot@italy.it"));

    }

    @Test
    public void searchAccountByEmailTrue() throws InterruptedException, ExecutionException, IOException {
        RequiredTrainingPlanDAO requiredTrainingPlanDAO = Mockito.mock(RequiredTrainingPlanDAO.class) ;
        RequiredTrainingPlan a = new RequiredTrainingPlan();
        a.setEmail("mail@mail.com");
        Mockito.when(requiredTrainingPlanDAO.findAccountByEmail(anyString())).thenReturn(a);
        RequiredTrainingPlanService service  = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDAO);
        assertEquals(true, service.searchAccountByEmail("cliente@gmail.com"));
    }


    @Test
    public void changeRequestFalse() throws IOException, ExecutionException, InterruptedException {
        RequiredTrainingPlanDAO requiredTrainingPlanDAO = Mockito.mock(RequiredTrainingPlanDAO.class) ;
        when(requiredTrainingPlanDAO.updateRequest(anyString(), anyInt())).thenReturn(false);
        RequiredTrainingPlanService service  = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDAO);
        assertEquals(false, service.changeRequest("cliente@gmail.com", 0));
    }

    @Test
    public void changePasswordTrue() throws IOException, ExecutionException, InterruptedException {
        RequiredTrainingPlanDAO requiredTrainingPlanDAO = Mockito.mock(RequiredTrainingPlanDAO.class) ;
        when(requiredTrainingPlanDAO.updateRequest(anyString(),anyInt())).thenReturn(true);
        RequiredTrainingPlanService service  = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDAO);
        assertEquals(true, service.changeRequest("cliente@gmail.com", 0));
    }

    @BeforeAll
    static void deleteAccountifExist() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> list_account = DBConnection.getConnection()
                .collection("RequiredTrainingPlan").whereEqualTo("email", "trainerino@testing.com").get().get().getDocuments();
        for (QueryDocumentSnapshot document : list_account) {
            document.getReference().delete();
        }
    }

    @AfterAll
    static void deleteAccountAfterInsert() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> list_account = DBConnection.getConnection().collection("RequiredTrainingPlan").whereEqualTo("email","trainerino@testing.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : list_account)
        {
            document.getReference().delete();
        }
    }

    @Test
    void registerAccountTrue() throws IOException, IllegalArgumentException,
            ExecutionException, InterruptedException {

        RequiredTrainingPlan require = new RequiredTrainingPlan("test@test.it", 0);

        RequiredTrainingPlanDAO requiredDAO = Mockito.mock(RequiredTrainingPlanDAO.class);

        RequiredTrainingPlanService requires = new RequiredTrainingPlanServiceImpl(requiredDAO);

        Mockito.when(requiredDAO.findAccountByEmail(anyString())).thenReturn(null);
        Mockito.when(requiredDAO.storeRequest(any())).thenReturn(true);
        RequiredTrainingPlan user_test = new RequiredTrainingPlan("test@test.it", 0);

        assertTrue(requires.registerRequest(user_test));

    }


    @BeforeAll
    static void insertbeforetest() throws IOException {

        RequiredTrainingPlan user = new RequiredTrainingPlan("trainerino@testing.com", 0);
        DBConnection.getConnection().collection("RequiredTrainingPlan").add(user);
    }

    @AfterAll
    static void deleteaftertest() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> list_account = DBConnection.getConnection().collection("RequiredTrainingPlan").whereEqualTo("email","trainerino@testing.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : list_account)
        {
            document.getReference().delete();
        }

    }

    @Test
    void registerAccountFalse() throws IOException, IllegalArgumentException,
            ExecutionException, InterruptedException {

        RequiredTrainingPlan require = new RequiredTrainingPlan("test25@test.it", 0);

        RequiredTrainingPlanDAO requiredDAO = Mockito.mock(RequiredTrainingPlanDAO.class);

        RequiredTrainingPlanService requires = new RequiredTrainingPlanServiceImpl(requiredDAO);

        Mockito.when(requiredDAO.findAccountByEmail(anyString())).thenReturn(null);
        Mockito.when(requiredDAO.storeRequest(any())).thenReturn(false);
        RequiredTrainingPlan user_test = new RequiredTrainingPlan("test1@test.it", 0);

        assertFalse(requires.registerRequest(user_test));

    }
}
