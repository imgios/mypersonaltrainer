package it.unisa.c03.myPersonalTrainer.control.serviceImpl;
import it.unisa.c03.myPersonalTrainer.control.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.model.bean.Parameters;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
class ParametersServiceImplTest extends Mockito {

    ParametersService pservice = new ParametersServiceImpl();

    @Test
    void lenghtWeightNotValid() {
        String weight = "522222";
        String fatMass = "30%";
        String leanMass = "55%";
        String message = "lenght weight non valida";
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
        String message = "format weight non valido";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void lenghtLeanMassNotValid() {
        String weight = "50";
        String fatMass = "3%";
        String leanMass = "55%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lenght fatMass non valida", exception.getMessage());
    }

    @Test
    void formatfatMassNotValid() {
        String weight = "50";
        String fatMass = "30X%";
        String leanMass = "55%";
        String message = "format fatMass non valido";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("format fatMass non valido", exception.getMessage());
    }

    @Test
    void formatleanMassNotValid() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "55X%";
        String message = "format leanMass non valido";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("format leanMass non valido", exception.getMessage());
    }

    @Test
    void lenghtleanMassNotValid() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "5%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pservice.createParameters(weight, leanMass, fatMass);
        });
        assertEquals("lenght leanMass non valida", exception.getMessage());
    }

    @Test
    void finalTest() {
        String weight = "50";
        String fatMass = "30%";
        String leanMass = "50%";
        assertEquals(Parameters.class, pservice.createParameters(weight, leanMass, fatMass).getClass());
    }
}