package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DBConnection {
    private static Firestore connection=null;
    private DBConnection(){}

    public static Firestore getConnection() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/java/Firebase/mypersonaltrainer-firebase-admin-sdk.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        connection= FirestoreClient.getFirestore();
        return connection;
    }

}
