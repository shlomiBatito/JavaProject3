package il.co.bat.shlomi.javaproject.controller;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import il.co.bat.shlomi.javaproject.R;


public class MainActivity extends Activity  implements View.OnClickListener{
    private Button addRideButton;
    private PlaceAutocompleteFragment placeAutocompleteFragment1;
    private PlaceAutocompleteFragment placeAutocompleteFragment2;
    private Button button;
    private TextView statusTextView;

    String from , to ;


    Location locationA = new Location("A");//= new Location(from);
    Location locationB = new Location("B") ;//= new Location(to);



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-06 13:00:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        placeAutocompleteFragment1 = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById( R.id.place_autocomplete_fragment1 );
        placeAutocompleteFragment2 = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById( R.id.place_autocomplete_fragment2 );
        button = (Button)findViewById( R.id.button );
        statusTextView = (TextView)findViewById( R.id.statusTextView );

        button.setOnClickListener( this );


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    public void AddRide(View v) {
       if(v== addRideButton)
        {
            setContentView(R.layout.activity_main);
            findViews();

        }
    }

    @Override
    public void onClick(View v) {

    }
}
