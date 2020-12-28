package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
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
        List<QueryDocumentSnapshot> lqds = connection.collection("appointment").
                whereEqualTo("date", appuntamento.getDate())
                .whereEqualTo("time", appuntamento.getTime()).
                        get().get().getDocuments();
        for (QueryDocumentSnapshot document : lqds) {
            document.getReference().delete();

        }
        return true;
    }
}
