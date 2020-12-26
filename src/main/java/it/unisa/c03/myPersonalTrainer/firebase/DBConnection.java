package it.unisa.c03.myPersonalTrainer.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


/*Classe che permetterà alle altre componenti del sistema di interagire con il database
* il costruttore della classe è privato in modo da usare metodi statici che non appartengno ad istanze della classe ma
* alla classe stessa*/
public final class DBConnection {
    private static Firestore singleton;
    private DBConnection(){}



    /* ritornerà un oggetto di tipo Firestore in grado di comunicare con il database
    * dichiarato come static così da appartenere alla classe e non ad un'istanza della stessa*/
    public static Firestore getConnection() throws IOException {

        if(singleton == null)
        {

        try {

            FirebaseOptions options=new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault()).build();
            singleton=FirestoreClient.getFirestore(FirebaseApp.initializeApp(options));
            return singleton;
        }
        catch (FileNotFoundException ignored){}

        }
        return singleton ;
    }


}
