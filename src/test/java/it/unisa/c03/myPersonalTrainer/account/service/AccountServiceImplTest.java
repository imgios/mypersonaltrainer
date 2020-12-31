package it.unisa.c03.myPersonalTrainer.account.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    //AccountService service = new AccountServiceImpl();


    // TC_1.3_1
    @Test
    public void emailLengthNotValid() {
        String mail = "p@l.it" ;
        String password = "password" ;
        String message = "Lunghezza email non valida" ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

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

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

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

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

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

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

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

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        assertEquals(true, service.checkCredentials(mail,password));
    }



    @Test
    public void searchAccountByEmailFalse() throws InterruptedException, ExecutionException, IOException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account();
        a.setEmail(null);
        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);

        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(false, service.searchAccountByEmail("mailnot@italy.it"));

    }


    @Test
    public void searchAccountByEmailTrue() throws InterruptedException, ExecutionException, IOException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account();
        a.setEmail("mail@mail.com");
        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(true, service.searchAccountByEmail("cliente@gmail.com"));
    }



    @Test
    public void changePasswordFalse() throws IOException, ExecutionException, InterruptedException {


        /*
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
*/

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        when(accountDAO.updatePassword(anyString(),anyString())).thenReturn(false);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(false, service.changePassword("cliente@gmail.com", "nuovaPassword45"));
    }

    @Test
    public void changePasswordTrue() throws IOException, ExecutionException, InterruptedException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        when(accountDAO.updatePassword(anyString(),anyString())).thenReturn(true);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(true, service.changePassword("cliente@gmail.com", "nuovaPassword45"));
    }





    @BeforeAll
    static void deleteAccountifExist() throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> list_account = DBConnection.getConnection()
            .collection("Account").whereEqualTo("email", "test@test.it").get().get().getDocuments();
        for (QueryDocumentSnapshot document : list_account) {
            document.getReference().delete();
        }
    }

    @AfterAll
    static void deleteAccountAfterInsert() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> list_account = DBConnection.getConnection().collection("Account").whereEqualTo("email","test@test.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : list_account)
        {
            document.getReference().delete();
        }
    }



    @Test
    void registerAccountTrue() throws IOException, IllegalArgumentException,
        ExecutionException, InterruptedException {

        Account user = new Account("nome","cognome","1223232312321", "test@test.it", "Test0232", 0);

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
       //Account user = Mockito.mock(Account.class);

        AccountService accounts = new AccountServiceImpl(accountDAO);

        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(user);
        Mockito.when(accountDAO.saveAccount(any())).thenReturn(true);
        Account user_test = new Account("nome","cognome","1223232312321", "test@test.it", "Test0232", 0);

        //accountDAO.saveAccount(account);
        assertTrue(accounts.registerAccount(user_test));

    }


    @Test
    void registerAccountFalse() throws IOException, IllegalArgumentException,
        ExecutionException, InterruptedException {

        Account user = new Account("nome", "cognome", "121212121212", "test@test.it", "Password12",
            0);

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
        AccountService accountS = new AccountServiceImpl(accountDAO);

        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(user);

        Account user_test = new Account("nome", "cognome", "121212121212", "test@test.it",
            "Password12", 0);

        // assertFalse(accountS.registerAccount(user_test));
        assertThrows(IllegalArgumentException.class, () -> {
            accountS.registerAccount(user_test);
            }
        );
    }
}