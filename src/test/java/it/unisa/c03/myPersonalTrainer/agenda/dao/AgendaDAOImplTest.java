package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class AgendaDAOImplTest {

    @Test
    void saveAppointment() throws IOException, ExecutionException, InterruptedException {
        String data="2020-07-13";
        String time="16:00";
        String mail="gigio@alfredo.it";
        Firestore connection=Mockito.mock(DBConnection.getConnection().getClass());
        CollectionReference coll=Mockito.mock(CollectionReference.class);
        Appointment app=new Appointment(data,time,mail);
        when(connection.collection(anyString())).thenReturn(coll);
        ApiFuture<DocumentReference> apiFuture= Mockito.mock(ApiFuture.class);
        when(coll.add(app)).thenReturn(apiFuture);
        when(apiFuture.isDone()).thenReturn(true);
        assertTrue(connection.collection("prova").add(app).isDone());

    }

    @Test
    void findAppointmetsByEmail() throws IOException, ExecutionException, InterruptedException {
       // Firestore connection = Mockito.mock(DBConnection.getConnection().getClass());
       // List<Appointment> appuntamenti=new ArrayList<Appointment>();
      //  connection.collection("Appointment").whereEqualTo("customerMail", "prova@prova.it").get().get().getDocuments();
    }

    @Test
    void findAppointmentByDate() {
        List<Appointment> appuntamenti=new ArrayList<Appointment>();
    }
}