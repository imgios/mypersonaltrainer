package it.unisa.c03.myPersonalTrainer.account.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountDAOImplTest {

    AccountDAO accountDAOTest = new AccountDAOImpl();
    @Test
    void findAccountByEmailTestNotFound() throws ExecutionException, InterruptedException {
        try {
            Firestore connection = Mockito.mock(DBConnection.getConnection().getClass());
            CollectionReference collectionRef = Mockito.mock(CollectionReference.class);

            when(connection.collection(anyString())).thenReturn(collectionRef);

            Query query = Mockito.mock(Query.class);

            when(collectionRef.whereEqualTo(anyString(),anyString())).thenReturn(query);

            ApiFuture<QuerySnapshot> api = Mockito.mock(ApiFuture.class);
            when(query.get()).thenReturn(api);

            QuerySnapshot qs = Mockito.mock(QuerySnapshot.class);
            when(api.get()).thenReturn(qs);

            List<QueryDocumentSnapshot> listQds = Mockito.mock(List.class);
            when(qs.getDocuments()).thenReturn(listQds);

            QueryDocumentSnapshot qds = Mockito.mock(QueryDocumentSnapshot.class);
            when(listQds.get(anyInt())).thenReturn(qds);

            when(connection.collection(anyString()).whereEqualTo(anyString(),anyString()).get().get().getDocuments().get(anyInt()).get(anyString())).thenReturn(null);
            Account account = new Account() ;
            account.setEmail(null);
            assertEquals(account.getEmail(), connection.collection("Account").whereEqualTo("email","emailerrata@prova.it").get().get().getDocuments().get(0).get("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccountByEmailTestFound() throws ExecutionException, InterruptedException {
        try {
            Firestore connection = Mockito.mock(DBConnection.getConnection().getClass());
            CollectionReference collectionRef = Mockito.mock(CollectionReference.class);

            when(connection.collection(anyString())).thenReturn(collectionRef);

            Query query = Mockito.mock(Query.class);

            when(collectionRef.whereEqualTo(anyString(),anyString())).thenReturn(query);

            ApiFuture<QuerySnapshot> api = Mockito.mock(ApiFuture.class);
            when(query.get()).thenReturn(api);

            QuerySnapshot qs = Mockito.mock(QuerySnapshot.class);
            when(api.get()).thenReturn(qs);

            List<QueryDocumentSnapshot> listQds = Mockito.mock(List.class);
            when(qs.getDocuments()).thenReturn(listQds);

            QueryDocumentSnapshot qds = Mockito.mock(QueryDocumentSnapshot.class);
            when(listQds.get(anyInt())).thenReturn(qds);

            when(connection.collection(anyString()).whereEqualTo(anyString(),anyString()).get().get().getDocuments().get(anyInt()).get(anyString())).thenReturn("cliente@gmail.com");
            Account account = new Account() ;
            account.setEmail("cliente@gmail.com");
            assertEquals(account.getEmail(), connection.collection("Account").whereEqualTo("email","cliente@gmail.com").get().get().getDocuments().get(0).get("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePassword() throws IOException {
        Firestore connection = Mockito.mock(DBConnection.getConnection().getClass());
        CollectionReference collectionRef = Mockito.mock(CollectionReference.class);

        when(connection.collection(anyString())).thenReturn(collectionRef);

        Query query = Mockito.mock(Query.class);

        when(collectionRef.whereEqualTo(anyString(),anyString())).thenReturn(query);


        DocumentReference docRef = Mockito.mock(DocumentReference.class);
        when(collectionRef.document(anyString())).thenReturn(docRef);

        ApiFuture<WriteResult> api = Mockito.mock(ApiFuture.class);
        when(docRef.update(anyString(),any())).thenReturn(api);


        when(connection.collection(anyString()).document(anyString()).update(anyString(),any()).isDone()).thenReturn(false);
        assertEquals(false, connection.collection("Account").document("documentId").update("password","newPassword1").isDone());

    }

    @Test
    void getAccountDocumentIdByEmail() {
    }
}