package it.unisa.c03.myPersonalTrainer.parameters.dao;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;

import java.util.List;

import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ParametersDAOImplTest {

    ParametersDAO parametersDAO = new ParametersDAOImpl();

    @BeforeAll
    static void bef() throws IOException {
        ParametersDAO parametersDAO = new ParametersDAOImpl();
        Parameters p1 = new Parameters(80, 48, 40, "test@utente.it");
        parametersDAO.insertParameters(p1);
    }



    @Test
    void populatAndtest() throws IOException {
        Parameters parameters = new Parameters(50, 25, 25, "prova@io.it");
        assertTrue(parametersDAO.insertParameters(parameters));
    }


    @Test
    void testMail() throws InterruptedException, ExecutionException, IOException {
        assertNotEquals(-1, parametersDAO.selectByMail("test@utente.it").size());
    }


    @AfterAll
    static void afterinsertaccount() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> list_param_insert = DBConnection
                .getConnection().collection("Parameters").whereEqualTo("mailClient", "prova@io.it").get().get().getDocuments();

        for (QueryDocumentSnapshot document : list_param_insert) {
            document.getReference().delete();
        }

        List<QueryDocumentSnapshot> list_param_insert1 = DBConnection
                .getConnection().collection("Parameters").whereEqualTo("mailClient", "test@utente.it").get().get().getDocuments();

        for (QueryDocumentSnapshot document : list_param_insert1) {
            document.getReference().delete();
        }
    }

}