package it.unisa.c03.myPersonalTrainer.account.dao;
import com.google.api.core.ApiFuture;


import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class AccountDAOImpl implements AccountDAO {

    @Override
    public void saveAccount(final Account utente) throws IOException {
            System.out.println("stampa dal DAO");
            System.out.println(utente);


        //inserimento della connessione al db per il salvataggio del documento
            //Firestore connection
            //connection.getCollections();
        DBConnection.getConnection().collection("Account").add(utente);
           // connection.collection("Account").add(utente);

        }

    @Override
    public Account findAccountByEmail(final String email) {

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
        Account accountBean = new Account();
        try {
            for (DocumentSnapshot document
                    : querySnapshot.get().getDocuments()) {

                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("name")));
                accountBean.setSurname(String.valueOf(document.get("surname")));
                accountBean.setPassword(
                        String.valueOf(document.get("password")));
                accountBean.setPhone(String.valueOf(document.get("phone")));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean;
    }

    @Override
    public void updatePassword(final String email, final String password) {

        //find document id
        String id = getAccountDocumentIdByEmail(email);

        // Update an existing document thanks to its id
        try {
            DocumentReference docRef = DBConnection.getConnection()
                    .collection("Account").document(id);

            // Update password field
            ApiFuture<WriteResult> future = docRef.update("password", password);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccountDocumentIdByEmail(final String email) {
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

        String id = "";
        try {
            for (DocumentSnapshot document
                    : querySnapshot.get().getDocuments()) {
                id = id + document.getId();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return id;
    }


}
