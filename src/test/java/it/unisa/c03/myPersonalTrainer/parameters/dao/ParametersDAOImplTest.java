package it.unisa.c03.myPersonalTrainer.parameters.dao;

import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ParametersDAOImplTest {

    ParametersDAO parametersDAO = new ParametersDAOImpl();

    @Test
    void populatAndtest() throws IOException {
        Parameters parameters = new Parameters(50, 25, 25, "prova@io.it");
        assertTrue(parametersDAO.insertParameters(parameters));
    }

    @Test
    void testMail() throws InterruptedException, ExecutionException, IOException {
        Parameters p1 = new Parameters(80, 48, 40, "test@utente.it");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(p1);
        assertEquals(1, parametersDAO.selectByMail("test@utente.it").size());
    }
}