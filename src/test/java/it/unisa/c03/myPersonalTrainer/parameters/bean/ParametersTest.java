package it.unisa.c03.myPersonalTrainer.parameters.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {


    @Test
    void TestBean() {

        double weight = 50;
        double leanMass = 22;
        double fatMass = 22;
        String mailClient = "mailclient@io.it";
        String insertionDate = "28/12/20";
        Parameters empty = new Parameters();
        assertNotNull(empty);
        Parameters p = new Parameters(weight, leanMass, fatMass, mailClient);
        assertNotNull(p);
        assertEquals(weight, p.getweight());
        assertEquals(leanMass, p.getleanMass());
        assertEquals(fatMass, p.getfatMass());
        assertEquals(mailClient, p.getMailClient());
        assertEquals(insertionDate, p.getinsertionDate());
        empty.setfatMass(fatMass);
        empty.setweight(weight);
        empty.setleanMass(leanMass);
        empty.setMailClient(mailClient);
        empty.setinsertionDate(insertionDate);
        assertEquals(weight, empty.getweight());
        assertEquals(leanMass, empty.getleanMass());
        assertEquals(fatMass, empty.getfatMass());
        assertEquals(mailClient, empty.getMailClient());
        assertEquals(insertionDate, empty.getinsertionDate());
    }
}