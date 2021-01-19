package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AgendaDAOImpl implements AgendaDAO {

    @Override
    public boolean saveAppointment(Appointment appuntamento)
            throws IOException {
        Firestore connection = DBConnection.getConnection();
        ApiFuture<DocumentReference> doc = connection.
                collection("Appointment").add(appuntamento);
        return true;
    }

    @Override
    public List<Appointment> findAppointmetsByEmail(String mail) throws
            IOException, ExecutionException, InterruptedException {
        Firestore connection = DBConnection.getConnection();
        List<Appointment> appuntamenti = null;
        Query query = connection.collection("Appointment").
                whereEqualTo("customerMail", mail);
        ApiFuture<QuerySnapshot> querySnap = query.get();
        List<QueryDocumentSnapshot> docs = querySnap.get().getDocuments();
        Stream<Appointment> sa = docs.stream().map(queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Appointment.class));
        appuntamenti = sa.collect(Collectors.toList());
        return appuntamenti;
    }

    @Override
    public List<Appointment> findAppointmentByDate(String date) throws
            IOException, ExecutionException, InterruptedException {
        Firestore connection = DBConnection.getConnection();
        List<Appointment> appuntamenti = null;
        Query query = connection.collection("Appointment")
                .whereEqualTo("date", date);
        ApiFuture<QuerySnapshot> querySnapShot = query.get();
        List<QueryDocumentSnapshot> docs = querySnapShot.get().getDocuments();
        Stream<Appointment> sa = docs.stream().map(queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Appointment.class));
        appuntamenti = sa.collect(Collectors.toList());
        return appuntamenti;
    }

    @Override
    public boolean deleteappointment(Appointment appuntamento) throws
            IOException, ExecutionException, InterruptedException {

        Firestore connection = DBConnection.getConnection();
        List<QueryDocumentSnapshot> lqds = connection.collection("Appointment").
                whereEqualTo("date", appuntamento.getDate())
                .whereEqualTo("time", appuntamento.getTime()).
                        get().get().getDocuments();
        for (QueryDocumentSnapshot d : lqds) {
            String id = d.getId();
            connection.collection("Appointment").document(id).delete();
        }
        return true;
    }


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

    /**
     * find an availability by date.
     *
     * @param date to find availability.
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
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

    /**
     * remove an availability.
     *
     * @param availability the availability to remove.
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @return true
     */
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

    /**
     * search an availability with this time and date.
     *
     * @param date
     * @param time
     * @return the availability, null if there isn't
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
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
        if (dispo.size() == 0) {
            return null;
        } else {
            return dispo.get(0);
        }
    }
}
