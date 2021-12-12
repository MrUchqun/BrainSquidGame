package com.b12.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.b12.game.activities.GameActivity1;

public class LevelCompeletActivity extends AppCompatActivity {

    ImageView stars, retry, menu, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);
        stars = findViewById(R.id.complete_stars);
        retry = findViewById(R.id.complete_retry);
        menu = findViewById(R.id.complete_home);
        next = findViewById(R.id.complete_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
                editorLevelNumber.putInt("levelCount", 1);
                editorLevelNumber.apply();
                Intent intent = new Intent(LevelCompeletActivity.this, GameActivity1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
        editorLevelNumber.putInt("levelCount", 1);
        editorLevelNumber.apply();
        Intent intent = new Intent(LevelCompeletActivity.this, GameActivity1.class);
        startActivity(intent);
        finish();
    }
}
