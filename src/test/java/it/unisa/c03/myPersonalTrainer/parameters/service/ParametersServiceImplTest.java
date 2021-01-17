package it.unisa.c03.myPersonalTrainer.parameters.service;

import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParametersServiceImplTest {

    ParametersDAO parametersDAO = Mockito.mock(ParametersDAO.class);
    ParametersService pservice = new ParametersServiceImpl(parametersDAO);

    //TC_1.2_1
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

    //TC_1.2_2
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

    //TC_1.2_4
    @Test
    void lenghtFatMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "3%";
        String email = "parametri@test.it";

        String leanMass = "45%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass, email);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }

    //TC_1.2_5
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

    //TC_1.2_3
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
    void testServiceInsert() throws IOException {
        Mockito.when(parametersDAO.insertParameters(any())).thenReturn(true);
        assertEquals(true, pservice.saveParameters(any()));
    }

    @Test
    void testServiceInsertFalse() throws IOException {
        Mockito.when(parametersDAO.insertParameters(any())).thenReturn(false);
        assertEquals(false, pservice.saveParameters(any()));
    }

    @Test
    void testFindByEmailNull() {
        String email = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.getByMail(email);
        });
        assertEquals("Email non valida", exception.getMessage());
    }

    @Test
    void mailNonExist() throws InterruptedException, ExecutionException, IOException {
        ArrayList<Parameters> list = new ArrayList<>();
        Mockito.when(parametersDAO.selectByMail(anyString())).thenReturn(list);
        assertEquals(0, pservice.getByMail(anyString()).size());
    }

    @Test
    void mailExist() throws InterruptedException, ExecutionException, IOException {
        Parameters p1 = new Parameters(80, 48, 40, "test@utente.it");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(p1);
        Mockito.when(parametersDAO.selectByMail(anyString())).thenReturn(list);
        assertEquals(1, pservice.getByMail(anyString()).size());
    }
}