package it.unisa.c03.myPersonalTrainer.GestioneParametri.control.serviceImpl;

import it.unisa.c03.myPersonalTrainer.GestioneParametri.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.bean.Parameters;
import it.unisa.c03.myPersonalTrainer.GestioneParametri.service.ParametersServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParametersServiceImplTest extends Mockito {

    ParametersService pservice = new ParametersServiceImpl();

    @Test
    void lenghtWeightNotValid() {
        String weight = "522222";
        String fatMass = "30%";
        String leanMass = "55%";
        String message = "lunghezza peso non valida";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void formatWeightNotValid() {
        String weight = "5X";
        String fatMass = "30%";
        String leanMass = "55%";
        String message = "formato peso non valido";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void lenghtFatMassNotValid() {
        String weight = "50";
        String fatMass = "3%";
        String leanMass = "55%";
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
    void testFindByEmailNull() {

        String email = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.getByMail(email);
        });
        assertEquals("Email non valida", exception.getMessage());
    }


}