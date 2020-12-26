package it.unisa.c03.myPersonalTrainer.account.dao;

import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AccountDAOImpl implements AccountDAO {

    /**
     * this is an implementation of AccountDAO.
     */

    @Override
    public Collection<Account> getAccounts() {

        // Create a reference to the account collection
        CollectionReference db = null;

        try {
            db = DBConnection.getConnection().collection("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApiFuture<QuerySnapshot> accounts = db.get();

        List<Account> accountBean = null;

        /**
         * this method estracts accounts into database.
         **/

        try {
            accountBean = accounts.get()
                    .getDocuments()
                            .stream()
                                .map(queryDocumentSnapshot -> queryDocumentSnapshot
                                    .toObject(Account.class))
                                        .collect(Collectors.toList());

        } catch (InterruptedException e ) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean;

    }

}