package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * this class provides the methods to make the
 * CRUD operations with the availability.
 */
public final class AgendaDAOImpl
        implements AgendaDAO {

    /**
     * @param availability the availability to insert into database
     * @return true if the insertion occurred, false otherwise
     * @throws IOException
     */
    @Override
    public boolean insertAvailability(
            Availability availability)
            throws IOException {
        DBConnection.getConnection().collection(
                "Availability").add(availability);
        return true;
    }

    @Override
    public List<Availability> findAvailabilityByDate(
            String date)
            throws IOException, InterruptedException, ExecutionException {
        Firestore connection = DBConnection.getConnection();
        List<Availability> dispo = null;
        Query query = connection.collection("Availability")
                .whereEqualTo("date", date);
        ApiFuture<QuerySnapshot> querySnapShot =
                query.get();
        List<QueryDocumentSnapshot> docs =
                querySnapShot.get().getDocuments();
        Stream<Availability> sa =
                docs.stream().map(queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Availability.class));
        dispo = sa.collect(Collectors.toList());
        return dispo;
    }


    public boolean deleteAvailability(
            Availability availability)
            throws IOException, ExecutionException, InterruptedException {

        Firestore connection =
                DBConnection.getConnection();
        List<QueryDocumentSnapshot> lqds =
                connection.collection("Availability").
                whereEqualTo("date", availability.getDate())
                .whereEqualTo("time", availability.getTime()).
                        get().get().getDocuments();
        for (QueryDocumentSnapshot document : lqds) {
            document.getReference().delete();
        }
        return true;
    }


    @Override
    public Availability findAvailabilityByDateAndTime(
            String date, int time)
            throws IOException, InterruptedException, ExecutionException {
        Firestore connection = DBConnection.getConnection();
        List<Availability> dispo = null;
        Query query = connection.collection("Availability")
                .whereEqualTo("date", date).whereEqualTo("time", time);
        ApiFuture<QuerySnapshot> querySnapShot = query.get();
        List<QueryDocumentSnapshot> docs = querySnapShot.get().getDocuments();
        Stream<Availability> sa = docs.stream().map(queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Availability.class));
        dispo = sa.collect(Collectors.toList());
        return dispo.get(0);
    }
}
