package it.unisa.c03.myPersonalTrainer.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


/*Classe che permetterà alle altre componenti del sistema di interagire con il database
* il costruttore della classe è privato in modo da usare metodi statici che non appartengno ad istanze della classe ma
* alla classe stessa*/
public class DBConnection {
    private static DBConnection singleton;
    private DBConnection(){}


    public static DBConnection getInstance()
    {
        if (singleton==null)
        {
            singleton=new DBConnection();
        }
        return singleton;
    }


    /* ritornerà un oggetto di tipo Firestore in grado di comunicare con il database
    * dichiarato come static così da appartenere alla classe e non ad un'istanza della stessa*/
    public static Firestore getConnection() throws IOException {
        Firestore connection=null;
        try {

            FirebaseOptions options=new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault()).build();
            connection=FirestoreClient.getFirestore( FirebaseApp.initializeApp(options));
            return connection;
        }
        catch (FileNotFoundException ignored){}
        return connection ;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String,String> dati=new HashMap<String, String>();
        dati.put("nome","umberto");
        dati.put("ruolo","0");
        DBConnection.getConnection().collection("dumb").document().set(dati);
    }
}
