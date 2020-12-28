package it.unisa.c03.myPersonalTrainer.agenda.bean;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AppointmentTest {

    @Test
    void TestBean() {
        String data="2020-07-13";
        String time="16:00";
        String mail="gigio@alfredo.it";
        Appointment empty=new Appointment();
        assertNotNull(empty);
        Appointment appuntamento= new Appointment(data,time,mail);
        assertNotNull(appuntamento);
        assertEquals(data,appuntamento.getDate());
        assertEquals(time,appuntamento.getTime());
        assertEquals(mail,appuntamento.getCustomerMail());
        empty.setDate(data);
        empty.setTime(time);
        empty.setCustomerMail(mail);
        assertEquals(data,empty.getDate());
        assertEquals(time,empty.getTime());
        assertEquals(mail,empty.getCustomerMail());



    }
}