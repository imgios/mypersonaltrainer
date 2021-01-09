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

    @Test
    void lenghtWeightNotValid() throws IOException {
        String weight = "2";
        String fatMass = "30%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza peso non valida", exception.getMessage());
    }
    @Test
    void lenghtWeightNotValidMax() throws IOException {
        String weight = "180";
        String fatMass = "30%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("valore peso non valido", exception.getMessage());
    }

    @Test
    void lenghtWeightNotValidMin() throws IOException {
        String weight = "20";
        String fatMass = "30%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("valore peso non valido", exception.getMessage());
    }


    @Test
    void valuesNull() throws IOException {
        String weight = null;
        String fatMass = null;
        String leanMass = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("valori mancanti", exception.getMessage());
    }

    @Test
    void formatWeightNotValid() throws IOException {
        String weight = "5X";
        String fatMass = "30%";
        String leanMass = "55%";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato peso non valido", exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "3%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValidMax() throws IOException {
        String weight = "50";
        String fatMass = "99%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }


    @Test
    void formatfatMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30X%";
        String leanMass = "55%";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato massa grassa non valido", exception.getMessage());
    }

    @Test
    void formatleanMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "55X%";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato massa magra non valido", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValid() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "5%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa magra non valida", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValidMax() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "99%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa magra non valida", exception.getMessage());
    }

    @Test
    void finalTest() throws IOException {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "50%";
        assertEquals(Parameters.class, pservice.createParameters(weight, leanMass, fatMass).getClass());
    }

    @Test
    void testServiceInsert() throws IOException {
        Mockito.when(parametersDAO.insertParameters(any())).thenReturn(true);
        assertEquals(true, pservice.insertParametersDB(any()));
    }

    @Test
    void testServiceInsertFalse() throws IOException {
        Mockito.when(parametersDAO.insertParameters(any())).thenReturn(false);
        assertEquals(false, pservice.insertParametersDB(any()));
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