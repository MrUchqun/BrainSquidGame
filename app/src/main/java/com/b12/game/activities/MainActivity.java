package com.b12.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.b12.game.R;

public class MainActivity extends AppCompatActivity {

    private CardView exitCardView, aboutUsCardView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this object changes starus bar !!!
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(this);
        exitCardView = findViewById(R.id.exit);
        exitCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aboutUsCardView = findViewById(R.id.about_us);
        aboutUsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class );
                startActivity(intent);
            }
        });

        CardView cardView = findViewById(R.id.play_game_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity1.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.play_game_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SecondGameActivity.class);
                startActivity(intent1);
            }
        });

    }
}