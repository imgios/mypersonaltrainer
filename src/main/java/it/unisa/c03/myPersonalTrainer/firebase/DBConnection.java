package it.unisa.c03.myPersonalTrainer.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class DBConnection {
    private static DBConnection singleton;

    private DBConnection() {
    }


    public static DBConnection getInstance() {
        if (singleton == null) {
            singleton = new DBConnection();
        }
        return singleton;
    }


    public static Firestore getConnection() throws IOException {
        Firestore connection = null;
        try {

            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault()).build();
            FirebaseApp firebaseApp = null;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            } else {
                firebaseApp = FirebaseApp.initializeApp(options);
            }
            connection = FirestoreClient.getFirestore(firebaseApp);
            return connection;
        } catch (FileNotFoundException ignored) {
        }
        return connection;
    }
}