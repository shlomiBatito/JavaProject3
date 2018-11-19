package il.co.bat.shlomi.javaproject.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import il.co.bat.shlomi.javaproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addRideButton;
    private void findViews() {
        addRideButton = (Button) findViewById(R.id.addRideButton);

        addRideButton.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

    @Override
    public void onClick(View v) {
       if(v== addRideButton)
        {}
    }
}
