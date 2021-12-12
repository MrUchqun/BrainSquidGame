package com.b12.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.b12.game.activities.GameActivity1;

public class LevelCompeletActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);
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
