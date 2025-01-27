package it.unisa.c03.myPersonalTrainer.firebase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest
{
    @Test
    public void getConnectionTest() throws IOException {
        assertNotNull(DBConnection.getConnection());
    }
    @Test
    public void TestRead() throws IOException, ExecutionException, InterruptedException {
        String test="dumb";
        String dbread=null;
        dbread=DBConnection.getConnection().collection("dumb").whereEqualTo("name","dumb").get().get().getDocuments().get(0).get("name").toString();
        assertEquals(test,dbread);
    }

}