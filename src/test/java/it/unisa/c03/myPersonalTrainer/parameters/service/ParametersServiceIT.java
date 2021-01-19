package it.unisa.c03.myPersonalTrainer.parameters.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParametersServiceIT {

    ParametersDAO parametersDAO = new ParametersDAOImpl();
    ParametersService pservice = new ParametersServiceImpl(parametersDAO);

    @BeforeAll
    static void bef() throws IOException {
        ParametersDAO parametersDAO = new ParametersDAOImpl();
        ParametersService parametersService = new ParametersServiceImpl(parametersDAO);
        Parameters p1 = new Parameters(80, 48, 40, "parametri@test.it");
        parametersDAO.insertParameters(p1);
    }


    @Test
    void insertTest() throws IOException, ExecutionException, InterruptedException {
        Parameters p = new Parameters(80, 28, 33, "parametri@test.it");
        assertEquals(true, pservice.saveParameters(p));
    }

    @Test
    void mailExist() throws InterruptedException, ExecutionException, IOException {
        assertNotEquals(0, pservice.getByMail("parametri@test.it").size());
    }


    @Test
    void lenghtWeightNotValid() throws IOException {
        String weight = "2";
        String fatMass = "30%";
        String leanMass = "55%";
        String email = "parametri@test.it";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza peso non valida", exception.getMessage());
    }

    @Test
    void lenghtWeightNotValidMax() throws IOException {
        String weight = "180";
        String fatMass = "30%";
        String leanMass = "55%";
        String email = "parametri@test.it";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("valore peso non valido", exception.getMessage());
    }

    @Test
    void lenghtWeightNotValidMin() throws IOException {
        String weight = "20";
        String fatMass = "30%";
        String leanMass = "55%";
        String email = "parametri@test.it";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("valore peso non valido", exception.getMessage());
    }


    @Test
    void valuesNull() throws IOException {
        String weight = null;
        String fatMass = null;
        String leanMass = null;
        String email = "parametri@test.it";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("valori mancanti", exception.getMessage());
    }

    @Test
    void formatWeightNotValid() throws IOException {
        String weight = "5X";
        String fatMass = "30%";
        String email = "parametri@test.it";

        String leanMass = "55%";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("formato peso non valido", exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "3%";
        String email = "parametri@test.it";

        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValidMax() throws IOException {
        String weight = "50";
        String fatMass = "99%";
        String email = "parametri@test.it";

        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }


    @Test
    void formatfatMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30X%";
        String leanMass = "55%";
        String email = "parametri@test.it";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("formato massa grassa non valido", exception.getMessage());
    }

    @Test
    void formatleanMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "55X%";
        String email = "parametri@test.it";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("formato massa magra non valido", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String email = "parametri@test.it";

        String leanMass = "5%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza massa magra non valida", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValidMax() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String email = "parametri@test.it";
        String leanMass = "99%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza massa magra non valida", exception.getMessage());
    }

    @Test
    void finalTest() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "50%";
        String email = "parametri@test.it";

        assertEquals(Parameters.class, pservice.createParameters(weight, leanMass, fatMass, email).getClass());
    }



    @Test
    void testFindByEmailNull() {
        String email = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.getByMail(email);
        });
        assertEquals("Email non valida", exception.getMessage());
    }



    @AfterAll
    static void spopola() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection
                .getConnection().collection("Parameters").whereEqualTo("mailClient",
                        "parametri@test.it").get().get().getDocuments();
        for (QueryDocumentSnapshot document : lqds) {
            document.getReference().delete();
        }
    }
}
