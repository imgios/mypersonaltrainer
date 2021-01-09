package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RequiredTrainingPlanDAOImplTest {

    static RequiredTrainingPlan requireTrainingPlanTest ;
    //AccountDAO dao = new AccountDAOImpl();
    @BeforeAll
    static void setUp() throws IOException {

        requireTrainingPlanTest = new RequiredTrainingPlan("hismail@italy.com",0);
        DBConnection.getConnection().collection("RequiredTrainingPlan").add(requireTrainingPlanTest);
    }


    @AfterAll
    static void clean() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("RequiredTrainingPlan").whereEqualTo("email","hismail@italy.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }


    @Test
    void findTheAccountByEmail() throws InterruptedException, ExecutionException, IOException {

        RequiredTrainingPlanDAO dao = new RequiredTrainingPlanDAOImpl();
        RequiredTrainingPlan accountToSearch = dao.findAccountByEmail("hismail@italy.com");
        assertEquals(requireTrainingPlanTest.getEmail(),dao.findAccountByEmail("hismail@italy.com").getEmail());
    }


    @Test
    void updateRequest() throws IOException, ExecutionException, InterruptedException {
        RequiredTrainingPlanDAO dao = new RequiredTrainingPlanDAOImpl();

        assertEquals(true, dao.updateRequest("email@testing.com",0));
    }



    @AfterAll
    static void afterinsertaccount() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Account").whereEqualTo("email","admin@admin.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @Test
    void storeRequest() throws IOException {
        RequiredTrainingPlan requiredTrainingPlan = new RequiredTrainingPlan("admin@admin.it", 1);

        RequiredTrainingPlanDAO adao = new RequiredTrainingPlanDAOImpl();
        assertTrue(adao.storeRequest(requiredTrainingPlan));
    }

}
