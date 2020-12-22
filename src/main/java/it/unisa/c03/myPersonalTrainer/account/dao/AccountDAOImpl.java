package it.unisa.c03.myPersonalTrainer.account.dao;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void saveAccount(Account utente) throws IOException {
            System.out.println("stampa dal DAO");
            System.out.println(utente);


        //inserimento della connessione al db per il salvataggio del documento
            //Firestore connection
            //connection.getCollections();
        DBConnection.getConnection().collection("Account").add(utente);
           // connection.collection("Account").add(utente);

        }

}
