package it.unisa.c03.myPersonalTrainer.Account.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.Account.Firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.Account.bean.Account;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountDAOImpl implements AccountDAO {

    public String getAccountDocumentIdByEmail(String email){

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

        String id = "" ;
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                 id = id + document.getId();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return id ;
    }

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

        //create Bean to return
        Account accountBean = new Account() ;
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                
                    accountBean.setEmail(String.valueOf(document.get("email")));
                    accountBean.setName(String.valueOf(document.get("nome")));
                    accountBean.setSurname(String.valueOf(document.get("cognome")));
                    accountBean.setPassword(String.valueOf(document.get("password")));
                    accountBean.setPhone(String.valueOf(document.get("n_telefono")));
                    //TO DO: add role

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean ;
    }

    @Override
    public void updatePassword(String email , String password) {

        //find document id
        String id = getAccountDocumentIdByEmail(email);

        // Update an existing document thanks to its id
        try {
            DocumentReference docRef = DBConnection.getConnection().collection("Account").document(id);

            // Update password field
            ApiFuture<WriteResult> future = docRef.update("password", password);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
