package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class AgendaDAOImpl implements AgendaDAO {
    @Override
    public boolean saveAppointment(final Appointment appuntamento)
            throws IOException {
        Firestore connection = DBConnection.getConnection();
        try {
            connection.collection("Appointment").add(appuntamento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> findAppointmetsByEmail(final String mail) throws
            IOException, ExecutionException, InterruptedException {
        Firestore connection = DBConnection.getConnection();
        List<Appointment> appuntamenti = null;
        Query query = connection.collection("Appointment").
                whereEqualTo("customerMail", mail);
        ApiFuture<QuerySnapshot> querySnap = query.get();
        for (DocumentSnapshot document : querySnap.get().getDocuments()) {
            appuntamenti.add(document.toObject(Appointment.class));
        }
        return appuntamenti;
    }

    @Override
    public List<Appointment> findAppointmentByDate(Date date) {
        Timestamp x=Timestamp.of(date);
        date.getMonth();
        return null;
    }
}
