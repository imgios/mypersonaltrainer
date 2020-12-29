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
    public Account findAccountByEmail(String email)
            throws IOException, ExecutionException, InterruptedException {

        // Create a reference to the account collection
        CollectionReference accounts = null;

            accounts = DBConnection.getConnection().collection("Account");


        // Create a query against the collection.
        Query query = accounts.whereEqualTo("email", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return
        Account accountBean = new Account();

            for (DocumentSnapshot document
                    : querySnapshot.get().getDocuments()) {

                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("name")));
                accountBean.setSurname(String.valueOf(document.get("surname")));
                accountBean.setPassword(
                        String.valueOf(document.get("password")));
                accountBean.setPhone(String.valueOf(document.get("phone")));
            }


        return accountBean;
    }

    /**
     * This DAO method changes the password of an account.
     * @param email of the account
     * @param password updated
     * @return true after the change has taken place
     */
    @Override
    public boolean updatePassword(String email, String password)
            throws IOException, ExecutionException, InterruptedException {

        //find document id
        String id = getAccountDocumentIdByEmail(email);

        // Update an existing document thanks to its id

            DocumentReference docRef = DBConnection.getConnection()
                    .collection("Account").document(id);

            // Update password field
            ApiFuture<WriteResult> future = docRef.update("password", password);

            return true;

    }

    /**
     * This DAO method looks for the document id of an account.
     * @param email of the account
     * @return the document Id
     */
    @Override
    public String getAccountDocumentIdByEmail(String email)
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the account collection
        CollectionReference accounts = null;

            accounts = DBConnection.getConnection().collection("Account");


        // Create a query against the collection.
        Query query = accounts.whereEqualTo("email", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        String id = "";

            for (DocumentSnapshot document
                    : querySnapshot.get().getDocuments()) {
                id = id + document.getId();
            }


        return id;
    }


}
