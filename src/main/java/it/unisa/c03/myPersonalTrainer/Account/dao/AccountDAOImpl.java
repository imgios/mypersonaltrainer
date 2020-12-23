package it.unisa.c03.myPersonalTrainer.Account.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.Account.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.Account.bean.Account;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountDAOImpl implements AccountDAO {


    public Account findAccountByEmail(String email) {

        // Create a reference to the account collection
        CollectionReference accounts = null;
        try {
            accounts = DBConnection.getConnection().collection("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a query against the collection.
        Query query = accounts.whereEqualTo("email", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return document.get("email"));
        Account accountBean = new Account() ;
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("nome")));
                accountBean.setSurname(String.valueOf(document.get("cognome")));
                accountBean.setPassword(String.valueOf(document.get("password")));
                accountBean.setTelefono(String.valueOf(document.get("n_telefono")));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean ;
    }

    @Override
    public void updatePassword(String password) {

        // DBConnection.getConnection().collection("Account").orderBy()
    }
}
