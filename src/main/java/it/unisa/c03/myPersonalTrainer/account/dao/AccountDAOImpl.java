package it.unisa.c03.myPersonalTrainer.account.dao;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account verifyAccount(String email) throws IOException {


            CollectionReference accounts=null;
            try {
                accounts = DBConnection.getConnection().collection("Account"); //Problem is Here :(
            } catch (IOException e) {
                e.printStackTrace();
            }


            Query query = accounts.whereEqualTo("email", email);


            ApiFuture<QuerySnapshot> querySnapshot = query.get();


            Account accountBean = new Account();
            try {
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

                    accountBean.setEmail(String.valueOf(document.get("email")));
                    accountBean.setName(String.valueOf(document.get("nome")));
                    accountBean.setSurname(String.valueOf(document.get("cognome")));
                    accountBean.setPassword(String.valueOf(document.get("password")));
                    accountBean.setPhone(String.valueOf(document.get("n_telefono")));
                    accountBean.setRole(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return accountBean;
        }
}
