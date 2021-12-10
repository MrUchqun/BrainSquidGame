package com.b12.game;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.adapters.FirstGameLevelsAdapter;
import com.b12.game.getset.Level;

import java.util.ArrayList;

public class GameActivity1 extends AppCompatActivity {
    //    GridLayout gridLayout;
    private ArrayList<Level> list;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private FirstGameLevelsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        sharedPreferences = getSharedPreferences("LEVELS", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("LEVELS", MODE_PRIVATE).edit();

        for (int i = 1; i <= 15; i++) {
            editor.putInt(Integer.toString(i), R.drawable.ic_star_failed);
        }

        editor.apply();
        list = new ArrayList<>();
        levelsList();
        recyclerView = findViewById(R.id.levels_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setItemAnimator(null);
        adapter = new FirstGameLevelsAdapter(list, this);
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

    private void levelsList() {
        for (int i = 1; i <= 15; i++) {
            int sharedItem = sharedPreferences.getInt(Integer.toString(i), 0);
            list.add(new Level(Integer.toString(i), sharedItem));
        }
    }
//    @Override
//    public void onBackPressed() {
//        gridLayout.setVisibility(View.VISIBLE);
//        super.onBackPressed();
//    }


}