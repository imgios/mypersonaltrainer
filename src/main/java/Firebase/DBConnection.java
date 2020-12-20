package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


/*Classe che permetterà alle altre componenti del sistema di interagire con il database
* il costruttore della classe è privato in modo da usare metodi statici che non appartengno ad istanze della classe ma
* alla classe stessa*/
public class DBConnection {
    private static Firestore connection=null;
    private DBConnection(){}

    /* ritornerà un oggetto di tipo Firestore in grado di comunicare con il database
    * dichiarato come static così da appartenere alla classe e non ad un'istanza della stessa*/
    public static Firestore getConnection() throws IOException {
        if(connection!=null) return connection;
        FileInputStream serviceAccount =
                new FileInputStream("src/main/java/Firebase/mypersonaltrainer-firebase-admin-sdk.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        connection= FirestoreClient.getFirestore();
        return connection;
    }

    /*stub per testare la connessione*/
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println(DBConnection.getConnection().collection("dumb").whereEqualTo("name","dumb").get().get().getDocuments().get(0).get("name"));
    }
}
