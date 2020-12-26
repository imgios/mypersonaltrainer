package it.unisa.c03.myPersonalTrainer.Account.control.serviceImpl;


import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountService service = new AccountServiceImpl();

    // TC_1.3_1
    @Test
    public void emailLengthNotValid() {
        String mail = "p@l.it" ;
        String password = "password" ;
        String message = "Lunghezza email non valida" ;

        IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        Assertions.assertEquals(message,exception.getMessage());
    }

    // TC_1.3_2
    @Test
    public void emailFormatNotValid()
    {
        String mail = "client@@prova.it" ;
        String password = "password" ;
        String message = "Formato email non valido" ;

        IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        Assertions.assertEquals(message,exception.getMessage());
    }

    // TC_1.3_3
    @Test
    public void passwordLengthNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "" ;
        String message = "Lunghezza password non valida" ;

        IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        Assertions.assertEquals(message,exception.getMessage());
    }

    // TC_1.3_4
    @Test
    public void passwordFormatNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "prova" ;
        String message = "Formato password non valido" ;

        IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        Assertions.assertEquals(message,exception.getMessage());
    }

    // TC_1.3_5
    @Test
    public void finalTest()
    {
        String mail = "client@prova.it" ;
        String password = "password1." ;

        Assertions.assertEquals(true, service.checkCredentials(mail,password));
    }
}