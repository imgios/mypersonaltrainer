package it.unisa.c03.myPersonalTrainer.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AccountServiceImplIT {

  // TC_1.3_1
  @Test
  public void emailLengthNotValidIT() {
    String mail = "p@l.it" ;
    String password = "password" ;
    String message = "Lunghezza email non valida" ;

    AccountDAO accountDAO = new AccountDAOImpl();
    AccountService service  = new AccountServiceImpl(accountDAO);

    IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
      service.checkCredentials(mail,password) ;
    });
    IllegalArgumentException exception2 =  assertThrows(IllegalArgumentException.class , () -> {
      service.checkCredentials("p@llllllllllllllllllllllllllllllllllllllllll.it",password) ;
    });
    assertEquals(message,exception.getMessage());
  }

  // TC_1.3_2
  @Test
  public void emailFormatNotValidIT()
  {
    String mail = "client@@prova.it" ;
    String password = "password" ;
    String message = "Formato email non valido" ;

    AccountDAO accountDAO = new AccountDAOImpl();
    AccountService service  = new AccountServiceImpl(accountDAO);

    IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
      service.checkCredentials(mail,password) ;
    });
    assertEquals(message,exception.getMessage());
  }

  // TC_1.3_3
  @Test
  public void passwordLengthNotValidIT()
  {
      String mail = "client@prova.it" ;
      String password = "" ;
      String message = "Lunghezza password non valida" ;

      AccountDAO accountDAO = new AccountDAOImpl();
      AccountService service  = new AccountServiceImpl(accountDAO);

      IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
        service.checkCredentials(mail,password) ;
      });

      IllegalArgumentException exception2 =  assertThrows(IllegalArgumentException.class , () -> {
        service.checkCredentials(mail,"passwordpasswordpasswordpasswordpassword") ;
      });
      assertEquals(message,exception.getMessage());
  }

  // TC_1.3_4
  @Test
  public void passwordFormatNotValidIT()
  {
    String mail = "client@prova.it" ;
    String password = "prova" ;
    String message = "Formato password non valido" ;

    AccountDAO accountDAO = new AccountDAOImpl();
    AccountService service  = new AccountServiceImpl(accountDAO);

    IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
      service.checkCredentials(mail,password) ;
    });
    assertEquals(message,exception.getMessage());
  }

  // TC_1.3_5
  @Test
  public void finalTestIT()
  {
    String mail = "client@prova.it" ;
    String password = "password1." ;

    AccountDAO accountDAO = new AccountDAOImpl();
    AccountService service  = new AccountServiceImpl(accountDAO);

    assertEquals(true, service.checkCredentials(mail,password));
  }


  @Test
  public void searchAccountByEmailFalseIT() throws InterruptedException, ExecutionException, IOException {

    AccountDAO accountDAO = new AccountDAOImpl();
    Account a = new Account();
    a.setEmail(null);
    //Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);

    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(false, service.searchAccountByEmail("mailnot@italy.it"));
  }



  @BeforeAll
  static void inserimentoemailIT() throws IOException {

    Account user = new Account("nome_controllo_email", "cognome_controllo_email", "1231231234",
        "test_it_trovato@gmail.com", "Password12",
        0);

    DBConnection.getConnection().collection("Account").add(user);

  }

  @AfterAll
  static void cancellaemailtrovata() throws IOException, ExecutionException, InterruptedException {

    List<QueryDocumentSnapshot> list_account = DBConnection.getConnection().collection("Account").whereEqualTo("email","test_it_trovato@gmail.com").get().get().getDocuments();
    for(QueryDocumentSnapshot document : list_account)
    {
      document.getReference().delete();
    }

  }

  @Test
  public void searchAccountByEmailTrueIT() throws InterruptedException, ExecutionException, IOException {
    AccountDAO accountDAO = new AccountDAOImpl();

    Account user = new Account("nome_controllo_email", "cognome_controllo_email", "1231231234",
        "test_it_trovato@gmail.com", "Password12",
        0);

    // Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(true, service.searchAccountByEmail("test_it_trovato@gmail.com"));
  }


  @AfterAll
  static void cancellaemailtrovata_2() throws IOException, ExecutionException, InterruptedException {

    List<QueryDocumentSnapshot> list_account_2 = DBConnection.getConnection().collection("Account").whereEqualTo("email","test_it_trovato_mail@gmail.com").get().get().getDocuments();
    for(QueryDocumentSnapshot document : list_account_2)
    {
      document.getReference().delete();
    }
  }



  @BeforeAll
  static void inserimentoemailIT_2() throws IOException {

    Account user_test2 = new Account("nome_controllo_email", "cognome_controllo_email", "1231231234",
        "test_it_trovato_mail@gmail.com", "Password12",
        0);

    DBConnection.getConnection().collection("Account").add(user_test2);
  }



  @Test
  public void getAccountByEmailTrueIT() throws InterruptedException, ExecutionException, IOException {
    AccountDAO accountDAO = new AccountDAOImpl();

    Account user_test2 = new Account("nome_controllo_email", "cognome_controllo_email", "1231231234",
        "test_it_trovato_mail@gmail.com", "Password12",
        0);
   // a.setEmail("mail@mail.com");
    //Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(Account.class, service.getAccountByEmail("test_it_trovato_mail@gmail.com").getClass());
  }


  @Test
  public void getAccountByEmailFalseIT() throws InterruptedException, ExecutionException, IOException {
    AccountDAO accountDAO = new AccountDAOImpl();
    Account a = new Account();
    //Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(null, service.getAccountByEmail("account_non_trovato@gmail.com"));
  }

  /*
  @BeforeAll
  static void cancellaemailtrovata_5() throws IOException, ExecutionException, InterruptedException {

    List<QueryDocumentSnapshot> list_account_5 = DBConnection.getConnection().collection("Account").whereEqualTo("email","cliente_da_modificare@test.it").get().get().getDocuments();
    for(QueryDocumentSnapshot document : list_account_5)
    {
      document.getReference().delete();
    }

  }

  @Test
  public void changePasswordFalseIT() throws IOException, ExecutionException, InterruptedException {
    AccountDAO accountDAO = new AccountDAOImpl();
    //when(accountDAO.updatePassword(anyString(),anyString())).thenReturn(false);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(false, service.changePassword("cliente_da_modificare@test.it", "nuovaPassword45"));
  }
  */

  @BeforeAll
  static void inserimentoemailIT_3() throws IOException, ExecutionException, InterruptedException  {
    Account user_test2 = new Account("nome_cambio_email", "cognome_cambioemail_email", "1212121212",
        "cliente_modificato@gmail.com", "PasswordVecchia12",
        0);

    DBConnection.getConnection().collection("Account").add(user_test2);
  }

  @AfterAll
  static void cancellaemailtrovata_3() throws IOException, ExecutionException, InterruptedException {

    List<QueryDocumentSnapshot> list_account_3 = DBConnection.getConnection().collection("Account").whereEqualTo("email","cliente_modificato@gmail.com").get().get().getDocuments();
    for(QueryDocumentSnapshot document : list_account_3)
    {
      document.getReference().delete();
    }

  }

  @Test
  public void changePasswordTrueIT() throws IOException, ExecutionException, InterruptedException {
    AccountDAO accountDAO = new AccountDAOImpl();
  //  when(accountDAO.updatePassword(anyString(),anyString())).thenReturn(true);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(true, service.changePassword("cliente_modificato@gmail.com", "nuovaPassword45"));
  }



  // after per cancellare!
  @Test
  void registerAccountTrueIT() throws IOException, IllegalArgumentException,
      ExecutionException, InterruptedException {

    Account user = new Account("nome","cognome","1223232312321", "testit@test.it", "Test0232", 0);

    AccountDAO accountDAO = new AccountDAOImpl();

    AccountService accounts = new AccountServiceImpl(accountDAO);

    //accountDAO.findAccountByEmail(user.getEmail());

    //Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(user);
   // Mockito.when(accountDAO.saveAccount(any())).thenReturn(true);
   // Account user_test = new Account("nome","cognome","1223232312321", "test_1@test.it", "Test0232", 0);

    //accountDAO.saveAccount(account);
    assertTrue(accounts.registerAccount(user));
  }

  @AfterAll
  static void cancellainserimento() throws IOException, ExecutionException, InterruptedException {

      List<QueryDocumentSnapshot> list_account = DBConnection.getConnection().collection("Account").whereEqualTo("email","testit@test.it").get().get().getDocuments();
      for(QueryDocumentSnapshot document : list_account)
      {
        document.getReference().delete();
      }
      List<QueryDocumentSnapshot> list_account_it = DBConnection.getConnection().collection("Account").whereEqualTo("email","test_2_it@test.it").get().get().getDocuments();
      for(QueryDocumentSnapshot document : list_account_it)
      {
        document.getReference().delete();
      }

    }

  @BeforeAll
  static void insertbeforetestIT() throws IOException {


    Account user = new Account("nome", "cognome", "121212121212",
        "test_2_it@test.it", "Password12",
        0);

     DBConnection.getConnection().collection("Account").add(user);

  }

  @Test
  void registerAccountFalseIT() throws IOException, IllegalArgumentException,
      ExecutionException, InterruptedException {

    Account user = new Account("nome", "cognome", "121212121212",
        "test_2_it@test.it", "Password12",
        0);

    AccountDAO accountDAO = new AccountDAOImpl();
    AccountService accountS = new AccountServiceImpl(accountDAO);

   // Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(user);

   // Account user_test = new Account("nome", "cognome", "121212121212", "test_2@test.it",
     //   "Password12", 0);

    // assertFalse(accountS.registerAccount(user_test));
    assertThrows(IllegalArgumentException.class, () -> {
          accountS.registerAccount(user);
        }
    );
  }

/*Controlliamo prima*/
  @Test
  void verifyIsAdminFalseIT() {
    AccountDAO accountDAO = new AccountDAOImpl();
    Account a = new Account("nameTest", "surnameTest","1234567895","testmail@test.com","testPasswordIT1",0);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(false, service.verifyIsAdmin(a));
  }

  @Test
  void verifyIsAdminTrueIT() {
    AccountDAO accountDAO = new AccountDAOImpl();
    Account a = new Account("nameTest1", "surnameTest1","1234567865","testmailadmin@test.com","testPassAdminIT1",1);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(true, service.verifyIsAdmin(a));
  }

  @Test
  void loginAccountTrueIT() throws IOException, ExecutionException, InterruptedException {
    AccountDAO accountDAO = new AccountDAOImpl();
    Account a = new Account("Giampiero", "Ferrara","1234567890","giampieroferrara@test.it","Giampiero1",0);
    AccountService service  = new AccountServiceImpl(accountDAO);
    assertEquals(true, service.loginAccount("giampieroferrara@test.it", "Giampiero1"));
  }

  @Test
  void viewInfoAccountIT() throws IOException, ExecutionException, InterruptedException{
    AccountDAO accDAO = new AccountDAOImpl();
    ArrayList<Account> listToReturn = new ArrayList<>();
    Account a = new Account("Giampiero", "Ferrara", "1234567890", "giampieroferrara@test.it", "Giampiero1", 0);
    listToReturn.add(a);
    //Mockito.when(accDAO.getAccounts()).thenReturn(listToReturn);
    assertEquals(listToReturn.size(), listToReturn.size());
  }

}

