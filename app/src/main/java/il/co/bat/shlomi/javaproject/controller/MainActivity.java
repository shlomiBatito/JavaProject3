package il.co.bat.shlomi.javaproject.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import il.co.bat.shlomi.javaproject.R;
import il.co.bat.shlomi.javaproject.model.backend.DB_ManagerFactory;
import il.co.bat.shlomi.javaproject.model.datasource.DBManager_Firebase;
import il.co.bat.shlomi.javaproject.model.entities.Ride;

import static il.co.bat.shlomi.javaproject.R.*;


public class MainActivity extends Activity implements View.OnClickListener {

    private CardView addRideButton;
    private PlaceAutocompleteFragment placeAutocompleteFragment1;
    private PlaceAutocompleteFragment placeAutocompleteFragment2;

    private TextView enteredName;
    private TextView emailAddress;
    private TextView Celnumber;

    private Button getLocationButton;
    private Button stopUpdateButton;
    private Ride ride;

    Location locationA = new Location("A");//= new Location(from);
    Location locationB = new Location("B");//= new Location(to);
    // Acquire a reference to the system Location Manager
    LocationManager locationManager;
    // Define a listener that responds to location updates
    LocationListener locationListener;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-06 13:00:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        placeAutocompleteFragment1 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(id.place_autocomplete_fragment1);
        placeAutocompleteFragment2 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(id.place_autocomplete_fragment2);
        //two buttom of my location
        getLocationButton = (Button) findViewById(id.getLocationButton);
        getLocationButton.setOnClickListener(this);

        stopUpdateButton = (Button) findViewById(id.stopUpdateButton);
        stopUpdateButton.setOnClickListener(this);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        addRideButton = (CardView) findViewById(id.addRideButton);
        enteredName = (TextView) findViewById(id.enteredName);
        Celnumber = (TextView) findViewById(id.Celnumber);
        emailAddress = (TextView) findViewById(id.address);
        //  button.setOnClickListener( this );
        addRideButton.setOnClickListener(this);
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //    Toast.makeText(getBaseContext(), location.toString(), Toast.LENGTH_LONG).show();
                //locationTextView.setText(getPlace(location));////location.toString());
                placeAutocompleteFragment1.setText(getPlace(location));
                // Remove the listener you previously added
                //  locationManager.removeUpdates(locationListener);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        placeAutocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationA.setLatitude(place.getLatLng().latitude);
                locationA.setLongitude(place.getLatLng().longitude);
                // .getAddress().toString();//get place details here
            }

            @Override
            public void onError(Status status) {

            }
        });

        placeAutocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                //  to = place.getAddress().toString();//get place details here
                locationB.setLatitude(place.getLatLng().latitude);
                locationB.setLongitude(place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {

            }
        });
    }

    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);

        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            stopUpdateButton.setEnabled(true);
            getLocationButton.setEnabled(false);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
                //  String stateName = addresses.get(0).getAddressLine(1);
                //  String countryName = addresses.get(0).getAddressLine(2);
                //  return stateName + "\n" + cityName + "\n" + countryName;
                return cityName;
            }

            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                stopUpdateButton.setEnabled(true);
                getLocationButton.setEnabled(false);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

     /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, 2550);
*/
        findViews();

    }


    @Override
    public void onClick(View v) {
        if (v == getLocationButton) {
            getLocation(); // Handle clicks for getLocationButton
        }
        if (v == stopUpdateButton) {
            // Remove the listener you previously added
            locationManager.removeUpdates(locationListener);
            stopUpdateButton.setEnabled(false);
            getLocationButton.setEnabled(true);
        }
        if (v == addRideButton) {
            ride = new Ride();

            ride.setCelNumber(Celnumber.getText().toString());
            ride.setName(enteredName.getText().toString());
            ride.setStartLocation(locationA);
            ride.setEndLocation(locationB);
            ride.setEmail(emailAddress.getText().toString());


            new AsyncTask<Void, Void, Void>() {
                /*@Override
                protected void onPostExecute(Long idResult) {
                    super.onPostExecute(idResult);
                    if (idResult>0)
                        Toast.makeText(getBaseContext(),"insert id: " + idResult, Toast.LENGTH_LONG).show();
                }*/
                @Override
                protected Void doInBackground(Void... voids) {
                    return DB_ManagerFactory.getBL().addRide(ride);
                }
            }.execute();


            enteredName.setText("");
            Celnumber.setText("");
            emailAddress.setText("");
            placeAutocompleteFragment1.setText("");
            placeAutocompleteFragment2.setText("");

        }

    }
}
























