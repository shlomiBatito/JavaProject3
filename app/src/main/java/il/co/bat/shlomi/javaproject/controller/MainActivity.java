package il.co.bat.shlomi.javaproject.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import il.co.bat.shlomi.javaproject.R;

public class MainActivity extends AppCompatActivity  {
    private Button addRideButton;
    private void findViews() {
        addRideButton = (Button) findViewById(R.id.addRideButton);

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
            setContentView(R.layout.addride);
            findViews();
        }
    }
}
