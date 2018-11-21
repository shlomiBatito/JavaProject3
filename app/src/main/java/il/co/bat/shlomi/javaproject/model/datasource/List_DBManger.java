package il.co.bat.shlomi.javaproject.model.datasource;

import android.content.ContentValues;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import il.co.bat.shlomi.javaproject.model.backend.DB_manager;
import il.co.bat.shlomi.javaproject.model.entities.Ride;


public class List_DBManger implements DB_manager {
    static List<Ride> rides;
    static {
        rides=new ArrayList<>();
    }


    @Override
    public void addRide(ContentValues ride)
    {


    }

    @Override
    public List<Ride> getRides() {
        return rides;
    }
    public Ride ContentValuesToRide(ContentValues ride)
    {
        Ride RealRide=new Ride();
        RealRide.setEmail(ride.getAsString("passangerEmail"));
        RealRide.setCelNumber(ride.getAsInteger("passangerCel"));








return RealRide;

    }
}
