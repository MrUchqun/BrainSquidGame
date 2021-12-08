package com.b12.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.b12.game.fragments.FragmentFirstGameAssignment;

public class GameActivity1 extends AppCompatActivity {
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

         gridLayout = findViewById(R.id.grid_steps);

        gridLayout.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new FragmentFirstGameAssignment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.game1, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                gridLayout.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void onBackPressed() {
        gridLayout.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}