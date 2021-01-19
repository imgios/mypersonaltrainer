package it.unisa.c03.myPersonalTrainer.trainingplan.bean;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.RequiredTrainingPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequiredTrainingPlanTest {

    @Test
    void testAccountBean() {

        String email = "gino@gmail.com";
        int required = 0;
        RequiredTrainingPlan account = new RequiredTrainingPlan(email, required);
        RequiredTrainingPlan account1 = new RequiredTrainingPlan(email, required);
        RequiredTrainingPlan voidAccount= new RequiredTrainingPlan();
        assertNotNull(account);
        String stringTest=account.toString();
        assertEquals(email, account.getEmail());
        assertEquals(required, account.getRequired());
        account1.setEmail(email);
        account1.setRequired(required);
        assertEquals(email, account1.getEmail());
        assertEquals(required, account1.getRequired());
        assertEquals(stringTest,account.toString());
        voidAccount.setEmail(email);
        voidAccount.setRequired(required);
    }
}