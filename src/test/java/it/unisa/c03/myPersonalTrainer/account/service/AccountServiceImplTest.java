package it.unisa.c03.myPersonalTrainer.account.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.Test;




import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountService service = new AccountServiceImpl();

    // TC_1.3_1
    @Test
    public void emailLengthNotValid() {
        String mail = "p@l.it" ;
        String password = "password" ;
        String message = "Lunghezza email non valida" ;

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.3_2
    @Test
    public void emailFormatNotValid()
    {
        String mail = "client@@prova.it" ;
        String password = "password" ;
        String message = "Formato email non valido" ;

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.3_3
    @Test
    public void passwordLengthNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "" ;
        String message = "Lunghezza password non valida" ;

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.3_4
    @Test
    public void passwordFormatNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "prova" ;
        String message = "Formato password non valido" ;

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.3_5
    @Test
    public void finalTest()
    {
        String mail = "client@prova.it" ;
        String password = "password1." ;

        assertEquals(true, service.checkCredentials(mail,password));
    }

    @Test
    public void searchAccountByEmailFalse()
    {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        when(accountDAO.findAccountByEmail(anyString())).thenReturn(new Account());
        assertEquals(false, service.searchAccountByEmail("mailnot@italy.it"));
    }

    @Test
    public void searchAccountByEmailTrue()
    {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        when(accountDAO.findAccountByEmail(anyString())).thenReturn(new Account());
        assertEquals(true, service.searchAccountByEmail("cliente@gmail.com"));
    }

    @Test
    public void changePassword() throws IOException {

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
}