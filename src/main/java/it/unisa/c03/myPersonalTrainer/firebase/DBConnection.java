package it.unisa.c03.myPersonalTrainer.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;


/**
 * class that allows connection to the database.
 */
public final class DBConnection {
    /**
     * connession to the database.
     */
    private static Firestore singleton;

    private DBConnection() {
    }


    /**
     * @return returns an object of type firestore with which it will be
     * possible to interact with the database, thanks to the credentials
     * generated by firebase
     * @throws IOException
     */
    public static Firestore getConnection() throws IOException {
        if (singleton == null) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .getApplicationDefault()).build();
            singleton = FirestoreClient.
                    getFirestore(FirebaseApp.initializeApp(options));
            return singleton;
        }
        return singleton;
    }
}
