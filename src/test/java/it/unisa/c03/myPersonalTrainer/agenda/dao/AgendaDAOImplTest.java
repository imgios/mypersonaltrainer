package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AgendaDAOImplTest {
    AgendaDAO agendaDAO = Mockito.mock(AgendaDAO.class);

    @Test
    void insertAvailability() throws IOException {
        doNothing().when(agendaDAO).insertAvailability(isA(Availability.class));


        /*Firestore connection = Mockito.mock(DBConnection.getConnection().getClass());
        CollectionReference collectionRef = Mockito.mock(CollectionReference.class);

       // collectionRef.
        Availability a = new Availability("2021-10-10",14);
        when(connection.collection(anyString())).thenReturn(collectionRef);
        ApiFuture apiFuture = Mockito.mock(ApiFuture.class);

        when(collectionRef.add(a)).thenReturn(apiFuture);

//        DocumentReference documentReference = Mockito.mock(DocumentReference.class);

        //when(apiFuture.isDone()).thenReturn(false);

        when(connection.collection(anyString()).add(a).isDone()).thenReturn(true);

        assertEquals(true, connection.collection("Availability").add(new Availability("2021-10-10",14)).isDone());*/

    }
}