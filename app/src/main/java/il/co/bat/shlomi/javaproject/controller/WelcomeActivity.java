package il.co.bat.shlomi.javaproject.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import il.co.bat.shlomi.javaproject.R;

import static il.co.bat.shlomi.javaproject.R.*;

public class WelcomeActivity extends AppCompatActivity {
    private  int mProgressStatus = 0;
    private ProgressBar mProgressBar;
    private Handler mHandler=new Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_welcome);
        mProgressBar = (ProgressBar) findViewById(id.progressBar);
        mProgressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100)
                {
                    mProgressStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }).start();


    }
}
