package com.b12.game.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentFirstGameAssignment extends Fragment {
    private FirstGameAdapter adapter;
    private ArrayList<FirstGameItem> gameItems;
    private ArrayList<FirstGameItem> tempItems;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private TextView levelTxt, circler_progress_txt;
    private ImageView imageViewInCard;
    private ProgressBar progressBarHorizontal;
    private final Random rnd = new Random();
    int counter = 0, timer = 0;
    CountDownTimer countDownTimer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_game_assignment, container, false);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(getActivity());
        tempItems = new ArrayList<>();
        gameItems = new ArrayList<>();
        circler_progress_txt = view.findViewById(R.id.progress_circular_text);
        progressBarHorizontal = view.findViewById(R.id.horizontal_progress_bar);
        recyclerView = view.findViewById(R.id.first_game_recycler);
        imageViewInCard = view.findViewById(R.id.first_game_card_image);
        levelTxt = view.findViewById(R.id.first_game_level_txt);
        progressBar = view.findViewById(R.id.progress_circular);
        relativeLayout = view.findViewById(R.id.first_game_answer_relative_layout);
        progressBarHorizontal.setVisibility(View.GONE);
        countDownTimer();

        generateImage();


        return view;
    }

    private void getRandomImageFromArray() {
        int index = 1;
        tempItems.add(new FirstGameItem(R.drawable.img_1, 0));
        tempItems.add(new FirstGameItem(R.drawable.img_2, 1));
        tempItems.add(new FirstGameItem(R.drawable.img_3, 2));
        tempItems.add(new FirstGameItem(R.drawable.img_4, 3));


            index = (int) (Math.random() * tempItems.size());

        gameItems.add(tempItems.get(index));
        imageViewInCard.setImageResource(index);
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setItemAnimator(null);
        adapter = new FirstGameAdapter(gameItems);
        recyclerView.setAdapter(adapter);


    }


    private void countDownTimer() {

        countDownTimer = new CountDownTimer(10*50*20, 100) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                int barVal = (counter) - ((int) (seconds / 60 * 100) + (int) (seconds % 60));
                progressBar.setProgress(barVal);
                circler_progress_txt.setText(String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format

            }

            @Override
            public void onFinish() {
                if (circler_progress_txt.getText().equals("00")) {
                    circler_progress_txt.setText("Timer Out");
                } else {
                    circler_progress_txt.setText("00");
                }
            }
        }.start();

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
