package it.unisa.c03.myPersonalTrainer.account.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean loginAccount(final String email, final String password)
            throws IOException {

        boolean result = false;
        Account account;
        account = verifyAccount(email, password);
        Account testAccount = new Account(account.getName(), account.getSurname(), account.getPhone(),
                account.getEmail(), account.getPassword(), account.getRole());
        //the email exists in the DB
        if (account.getEmail() != null) {
            if (email.equals(testAccount.getEmail())
                    && (password.equals(testAccount.getPassword()))) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }
    @Override
    public boolean checkCredentials(String email, String password)
            throws IllegalArgumentException {

        boolean result;

            // check email format
        if (!(email.matches("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
        {
            throw new IllegalArgumentException("Formato email non valido");
        } else if (!(password.matches("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$"))) {
            throw new IllegalArgumentException("Formato password non valido");
        } else {
            result = true;
               }
        return result;
    }

    @Override
    public boolean verifyIsAdmin(Account account) {
        if (account.getRole() == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public Account verifyAccount(String email, String password) throws IOException {
        CollectionReference accounts = null;
        try {
            accounts = DBConnection.getConnection().collection("Account");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Query query = accounts.whereEqualTo("email", email);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        Account accountBean = new Account();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                accountBean.setEmail(String.valueOf(document.get("email")));
                accountBean.setName(String.valueOf(document.get("name")));
                accountBean.setSurname(String.valueOf(document.get("surname")));
                accountBean.setPassword(String.valueOf(document.get("password")));
                accountBean.setPhone(String.valueOf(document.get("phone")));
                accountBean.setRole(Integer.parseInt(String.valueOf(document.get("role"))));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return accountBean;
    }
}