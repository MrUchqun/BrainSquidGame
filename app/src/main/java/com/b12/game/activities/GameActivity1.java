package com.b12.game.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;
import com.b12.game.adapters.FirstGameLevelsAdapter;
import com.b12.game.fragments.FragmentFirstGameAssignment;
import com.b12.game.getset.Level;

import java.util.ArrayList;

public class GameActivity1 extends AppCompatActivity implements FirstGameLevelsAdapter.OnLevelClickListener {
    //    GridLayout gridLayout;
    private ArrayList<Level> list;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private FirstGameLevelsAdapter adapter;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(this);
        linearLayout = findViewById(R.id.game1_linear2);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            creatingLevels();
        }

        sharedPreferences = getSharedPreferences("LEVELS", MODE_PRIVATE);


        list = new ArrayList<>();
        levelsList();
        recyclerView = findViewById(R.id.levels_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setItemAnimator(null);
        adapter = new FirstGameLevelsAdapter(list, this, this);
        recyclerView.setAdapter(adapter);
//         gridLayout = findViewById(R.id.grid_steps);
//
//        gridLayout.getChildAt(0).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment someFragment = new FragmentFirstGameAssignment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.game1, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
//                gridLayout.setVisibility(View.GONE);
//            }
//        });
    }

    private void creatingLevels() {
        SharedPreferences.Editor editorStars = getSharedPreferences("LEVELS", MODE_PRIVATE).edit();
        SharedPreferences.Editor editorStatus = getSharedPreferences("STATUS", MODE_PRIVATE).edit();

        for (int i = 1; i <= 15; i++) {
            editorStars.putInt(Integer.toString(i), R.drawable.stars_0);
            if (i > 3) {
                editorStatus.putBoolean(Integer.toString(i), false);
            }

        }

        editorStars.apply();
        editorStatus.apply();
    }

    private void levelsList() {
        for (int i = 1; i <= 15; i++) {
            int sharedItem = sharedPreferences.getInt(Integer.toString(i), 0);
            list.add(new Level(Integer.toString(i), sharedItem, false));
        }
    }

    @Override
    public void onLevelClicked(String level) {
        SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
        editorLevelNumber.putString("levelnum", level);
        editorLevelNumber.putInt("levelCount", 1);
        editorLevelNumber.apply();
        linearLayout.setVisibility(View.GONE);
        Fragment someFragment = new FragmentFirstGameAssignment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game1_linear1, someFragment); // give your fragment container id in first parameter
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
            linearLayout.setVisibility(View.VISIBLE);
            super.onBackPressed();


    }


}