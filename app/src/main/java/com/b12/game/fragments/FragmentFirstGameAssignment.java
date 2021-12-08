package com.b12.game.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;
import com.b12.game.SplashActivity;
import com.b12.game.adapters.FirstGameAdapter;
import com.b12.game.getset.FirstGameItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentFirstGameAssignment extends Fragment {
    private FirstGameAdapter adapter;
    private ArrayList<FirstGameItem> gameItems;
    private ArrayList<FirstGameItem> tempItems;
    private ArrayList<Integer> list;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private TextView levelTxt, circler_progress_txt;
    private ImageView imageViewInCard;
    private ProgressBar progressBarHorizontal;
    private final Random rnd = new Random();
    int counter = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_game_assignment, container, false);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(getActivity());
        tempItems = new ArrayList<>();
        gameItems = new ArrayList<>();
        list = new ArrayList<>();
        circler_progress_txt = view.findViewById(R.id.progress_circular_text);
        progressBarHorizontal = view.findViewById(R.id.horizontal_progress_bar);
        recyclerView = view.findViewById(R.id.first_game_recycler);
        imageViewInCard = view.findViewById(R.id.first_game_card_image);
        levelTxt = view.findViewById(R.id.first_game_level_txt);
        progressBar = view.findViewById(R.id.progress_circular);
        relativeLayout = view.findViewById(R.id.first_game_answer_relative_layout);
        progressBarHorizontal.setVisibility(View.GONE);
        imageViewInCard.setVisibility(View.GONE);
        countDownTimer();


        generateImage();


        return view;
    }

    private void getRandomImageFromArray() {
//        int index = 1;
//        tempItems.add(new FirstGameItem(R.drawable.img_1, 0));
//        tempItems.add(new FirstGameItem(R.drawable.img_2, 1));
//        tempItems.add(new FirstGameItem(R.drawable.img_3, 2));
//        tempItems.add(new FirstGameItem(R.drawable.img_4, 3));
//
//
//        index = (int) (Math.random() * tempItems.size());
//
//        gameItems.add(tempItems.get(index));
//        imageViewInCard.setImageResource(index);
    }

    private void generateImage() {

        tempItems.add(new FirstGameItem(R.drawable.img_1, 0));
        tempItems.add(new FirstGameItem(R.drawable.img_2, 1));
        tempItems.add(new FirstGameItem(R.drawable.img_3, 2));
        tempItems.add(new FirstGameItem(R.drawable.img_4, 3));
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            int value = random.nextInt(4);
            gameItems.add(tempItems.get(value));
        }
        HashMap<FirstGameItem, Integer> hashMap = new HashMap<>();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setItemAnimator(null);
        adapter = new FirstGameAdapter(gameItems);
        recyclerView.setAdapter(adapter);


    }

    private void countDownTimer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (counter <= 10) {
                    circler_progress_txt.setText("" + counter);
                    progressBar.setProgress(counter);
                    counter++;
                    handler.postDelayed(this::run, 1000);
                } else {
                    handler.removeCallbacks(this::run);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    circler_progress_txt.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    progressBarHorizontal.setVisibility(View.VISIBLE);
                    imageViewInCard.setVisibility(View.VISIBLE);
                    progressBarHorizontalTimer();
                }
            }
        }, 100);
    }


    private void progressBarHorizontalTimer() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBarHorizontal.setProgress(counter);
                if (counter == 100) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 100);
    }
}
