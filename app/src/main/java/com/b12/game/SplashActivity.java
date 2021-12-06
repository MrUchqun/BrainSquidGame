package com.b12.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread startTimer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                    Intent intent0 = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent0);
                    //overridePendingTransition(R.anim.fadin, R.anim.fadout);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        startTimer.start();
    }
}