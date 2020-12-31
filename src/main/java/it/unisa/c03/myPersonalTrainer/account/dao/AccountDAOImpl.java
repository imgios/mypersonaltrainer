package it.unisa.c03.myPersonalTrainer.account.dao;
 import com.google.api.core.ApiFuture;

 import com.google.cloud.firestore.CollectionReference;
 import com.google.cloud.firestore.DocumentSnapshot;

 import com.google.cloud.firestore.Query;
 import com.google.cloud.firestore.QuerySnapshot;

 import it.unisa.c03.myPersonalTrainer.account.bean.Account;
 import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

 import java.util.Collection;
 import java.util.List;
 import java.util.concurrent.ExecutionException;
 import java.io.IOException;
 import java.util.stream.Collectors;

public class AccountDAOImpl implements AccountDAO {

    /**
     * this function can check if the email is into the db.
     * @param email is the pk to find the user into the db
     * @return
     */
    @Override
    public Account findAccountByEmail(String email)
            throws IOException, ExecutionException,
            InterruptedException {

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
            accountBean.setRole(Integer.parseInt(
                    String.valueOf(document.get("role"))));
        }
        return accountBean;
    }
}
