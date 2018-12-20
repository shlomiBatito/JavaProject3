package il.co.bat.shlomi.javaproject.model.datasource;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import il.co.bat.shlomi.javaproject.model.backend.DB_manager;
import il.co.bat.shlomi.javaproject.model.entities.Ride;

public  class DBManager_Firebase implements DB_manager {

    private static DatabaseReference RidesRef;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        RidesRef = database.getReference("rides");
    }

    @Override
    public Void addRide(final Ride ride) {
        //rides.add(ride);
        String key = ride.getEmail();
        RidesRef.child(key).setValue(ride);
         return null;
    }
}
