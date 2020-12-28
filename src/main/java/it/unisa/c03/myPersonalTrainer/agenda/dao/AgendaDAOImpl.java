package it.unisa.c03.myPersonalTrainer.agenda.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Availability;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class AgendaDAOImpl implements AgendaDAO {


    @Override
    public void insertAvailability(Availability availability)
            throws IOException {
        DBConnection.getConnection().collection(
                "Availability").add(availability).isDone();
    }
}
