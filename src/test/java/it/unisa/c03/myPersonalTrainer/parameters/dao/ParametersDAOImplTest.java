package it.unisa.c03.myPersonalTrainer.parameters.dao;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ParametersDAOImplTest {

    ParametersDAO parametersDAO = new ParametersDAOImpl();

    //  questo pare funzionare, ma è necessario un controllo
    @AfterAll
    static void afterinsertaccount() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> list_param_insert = DBConnection
            .getConnection().collection("Account").whereEqualTo("email","prova@io.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : list_param_insert)
        {
            document.getReference().delete();
        }
    }

    @Test
    void populatAndtest() throws IOException {
        Parameters parameters = new Parameters(50, 25, 25, "prova@io.it");
        assertTrue(parametersDAO.insertParameters(parameters));
    }




    /*
    inserire after e before per non trovare l'elemento nel db
     */

    @Test
    void testMail() throws InterruptedException, ExecutionException, IOException {
        Parameters p1 = new Parameters(80, 48, 40, "test@utente.it");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(p1);
        assertEquals(1, parametersDAO.selectByMail("test@utente.it").size());
    }
}