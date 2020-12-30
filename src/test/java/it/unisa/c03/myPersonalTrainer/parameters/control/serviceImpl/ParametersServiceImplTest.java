package it.unisa.c03.myPersonalTrainer.parameters.control.serviceImpl;

import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParametersServiceImplTest {

    ParametersService pservice = new ParametersServiceImpl();
    ParametersDAO parametersDAO = Mockito.mock(ParametersDAO.class);

    @Test
    void lenghtWeightNotValid() {
        String weight = "2";
        String fatMass = "30%";
        String leanMass = "55%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza peso non valida", exception.getMessage());
    }

    @Test
    void formatWeightNotValid() {
        String weight = "5X";
        String fatMass = "30%";
        String leanMass = "55%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato peso non valido", exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValid() {
        String weight = "50";
        String fatMass = "3%";
        String leanMass = "55%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa grassa non valida", exception.getMessage());
    }

    @Test
    void formatfatMassNotValid() {
        String weight = "50";
        String fatMass = "30X%";
        String leanMass = "55%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato massa grassa non valido", exception.getMessage());
    }

    @Test
    void formatleanMassNotValid() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "55X%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("formato massa magra non valido", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValid() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "5%";
        doNothing().when(parametersDAO).insertParameters(isA(Parameters.class));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lunghezza massa magra non valida", exception.getMessage());
    }

    @Test
    void finalTest() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "50%";
        assertEquals(Parameters.class, pservice.createParameters(weight, leanMass, fatMass).getClass());
    }

    @Test
    /**
     * the email doesn't exists
     */
    void testFindByEmailThatNotExists() {
        ArrayList<Parameters> list = new ArrayList<Parameters>();
        when(parametersDAO.selectByMail(anyString())).thenReturn(list);
        String email = "provamail";
        assertEquals(list, pservice.getByMail(email));
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
    void testFindByEmailThatExists() {
        ArrayList<Parameters> list = new ArrayList<Parameters>();
        Parameters p = new Parameters(50, 33, 25, "prova@io.it");
        list.add(p);
        when(parametersDAO.selectByMail(anyString())).thenReturn(list);
        String email = "prova@io.it";
        assertNotEquals(pservice.getByMail("prova@io.it").size(), list.size());
    }
}