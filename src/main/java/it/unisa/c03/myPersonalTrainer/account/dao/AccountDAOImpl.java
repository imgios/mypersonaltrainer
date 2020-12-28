package it.unisa.c03.myPersonalTrainer.account.dao;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class AccountDAOImpl implements AccountDAO {

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

    @Override
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
/*
                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("nome")));
                accountBean.setSurname(String.valueOf(document.get("cognome")));
                accountBean.setPassword(String.valueOf(document.get("password")));
                accountBean.setPhone(String.valueOf(document.get("n_telefono")));
                inserimento del nome della tabella in inglese
 */
                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("name")));
                accountBean.setSurname(String.valueOf(document.get("surname")));
                accountBean.setPassword(String.valueOf(document.get("password")));
                accountBean.setPhone(String.valueOf(document.get("phone")));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean ;
    }


}
